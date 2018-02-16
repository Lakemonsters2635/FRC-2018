package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2635.robot.subsystems.Elevator.Height;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX upperMotor;
	WPI_TalonSRX lowerMotor1; 
	WPI_TalonSRX lowerMotor2; 
	
	public DigitalInput topLimitSwitch; 
	public DigitalInput bottomLimitSwitch; 
	
	//Arbitrary/Random encoder values for height of locations
	//Ground:0
	//Switch:2000
	//Scale:5000
	//Climb:7000
	
	//Arbitrary/Random encoder values for max elevator heights
	//Lower: 4000
	//Upper: 3000
	
	Height targetHeight;
	double lowerElevatorMax;
	double upperElevatorMax;
	
	public Elevator() {
		upperMotor  = new WPI_TalonSRX(RobotMap.ELEVATOR_UPPER_MOTOR_CHANNEL);
    	lowerMotor1 = new WPI_TalonSRX(RobotMap.ELEVATOR_LOWER_MOTOR1_CHANNEL);
    	lowerMotor2 = new WPI_TalonSRX(RobotMap.ELEVATOR_LOWER_MOTOR2_CHANNEL);
    	
    	bottomLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_LIMIT_SWITCH_IO_CHANNEL); 
    	topLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_TOP_LIMIT_SWITCH_IO_CHANNEL); 

    	
    	lowerMotor2.follow(lowerMotor1);
    	
    	encoderStart();
    	//TODO FAKE VALUES, UPDATE TO ACCURATE VALUES, Fix this...
    	// In inches (units)
    	
    	lowerElevatorMax = 4000;
    	upperElevatorMax = 3000;
    	//END FAKE VALUES
    	
    	targetHeight = Height.GROUND;
	}
	
	public void motorControl() {
		double height = targetHeight.height;
		double lowerHeight = 0;
		double upperHeight = 0;
		
		if(height>upperElevatorMax) {
			lowerHeight = upperElevatorMax;
			upperHeight = height - upperElevatorMax;
		} else {
			upperHeight = 0;
			lowerHeight = height;
		}
		
		lowerMotor1.set(ControlMode.MotionMagic, lowerHeight);
		upperMotor.set(ControlMode.MotionMagic, upperHeight);
		

	}
	
	public boolean setTargetHeight(Height height) {
		targetHeight = height;
		return true;
	}
	
	
	public double currentHeight() {
		
		double lowerHeight = lowerMotor1.getSelectedSensorPosition(0);
		double upperHeight = upperMotor.getSelectedSensorPosition(0);
		
		double totalHeight = lowerHeight+upperHeight;
		
		return totalHeight;
	}
	public boolean isLimitSwitchPressed(DigitalInput limitSwitch) {
		boolean result = false;
		try {
			result = !limitSwitch.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isFinished(){
		if (isLimitSwitchPressed(bottomLimitSwitch)) {
    		return true;
    	}
		
		if (isLimitSwitchPressed(topLimitSwitch)) {
    		return true;
    	}
		
    	
    	double upperEncoderPosition = upperMotor.getSelectedSensorPosition(0);
    	double lowerEncoderPosition = lowerMotor1.getSelectedSensorPosition(1);
		return false;
	}
	
	public boolean isWithinTolerance(Height height) {
		return (currentHeight() > (height.height - RobotMap.ELEVATOR_TOLERANCE) && currentHeight() < (height.height + RobotMap.ELEVATOR_TOLERANCE)); //
	}
	
	private void encoderStart() {
		upperMotor.setSelectedSensorPosition(0, 0, 0);
    	upperMotor.config_kP(0, 5, 0);
    	upperMotor.config_kI(0, 0, 0);
    	upperMotor.config_kD(0, 0, 0);
    	upperMotor.config_kF(0, 0, 0);
    	
    	lowerMotor1.setSelectedSensorPosition(0, 0, 0);
    	lowerMotor1.config_kP(0, 5, 0);
    	lowerMotor1.config_kI(0, 0, 0);
    	lowerMotor1.config_kD(0, 0, 0);
    	lowerMotor1.config_kF(0, 0, 0);
    	
    	
    	lowerMotor1.selectProfileSlot(0, 0);
    	upperMotor.selectProfileSlot(0, 0);
    	
    	lowerMotor1.configMotionAcceleration(RobotMap.ELEVATOR_VELOCITY, 0);
    	upperMotor.configMotionAcceleration(RobotMap.ELEVATOR_VELOCITY, 0);
    	
    	lowerMotor1.configMotionCruiseVelocity(RobotMap.ELEVATOR_VELOCITY, 0);
    	upperMotor.configMotionCruiseVelocity(RobotMap.ELEVATOR_VELOCITY, 0);
	}
	// No longer has fake values <3, this just sets the values for the ground, switch, scale, and climb heights that the elevator uses.
	public static enum Height {
		GROUND(RobotMap.ELEVATOR_GROUND_LOWER_HEIGHT), 
		SWITCH(RobotMap.ELEVATOR_SWITCH_LOWER_HEIGHT), 
		SCALE(RobotMap.ELEVATOR_SCALE_LOWER_HEIGHT), 
		CLIMB(RobotMap.ELEVATOR_CLIMB_LOWER_HEIGHT);
		
		public int height;
		private Height(int height) {
			this.height = height;
		}
	}
	
	
	public enum Height2 {
		//FHE TODO:  
		GROUND (RobotMap.ELEVATOR_GROUND_LOWER_HEIGHT, RobotMap.ELEVATOR_GROUND_UPPER_HEIGHT),
		SWITCH   (RobotMap.ELEVATOR_SWITCH_LOWER_HEIGHT, RobotMap.ELEVATOR_SWITCH_UPPER_HEIGHT),
		SCALE   (RobotMap.ELEVATOR_SCALE_LOWER_HEIGHT, RobotMap.ELEVATOR_SCALE_UPPER_HEIGHT),
		CLIMB    (RobotMap.ELEVATOR_CLIMB_LOWER_HEIGHT, RobotMap.ELEVATOR_CLIMB_UPPER_HEIGHT);


	    private final double lowerHeight;
	    private final double upperHeight;
	    Height2(double lowerHeight, double upperHeight) {
	        this.lowerHeight = lowerHeight;
	        this.upperHeight = upperHeight;
	    }
	    private double lowerHeight() { return lowerHeight; }
	    private double upperHeight() { return upperHeight; }
	}
	
	public  Command ElevatorUp() {
		Height targetHeight;
		 if(isWithinTolerance(Height.GROUND)) {
			 targetHeight = Height.SWITCH;
	     } else if(isWithinTolerance(Height.SWITCH)) {
	         targetHeight = Height.SCALE;
	     } else if(isWithinTolerance(Height.SCALE)) {
	         targetHeight = Height.CLIMB;
	     } else {
	        return null;
	     }
		 return new ElevatorCommand(targetHeight);
		 // Raises the elevator to the next level if its w/i a defined tolerance.
	}
	
	public  Command ElevatorMove(Height height) {

		 return new ElevatorCommand(height);
		 // Raises the elevator to the next level if its w/i a defined tolerance.
	}
	
	
	public  Command ElevatorDown(){
		Height targetHeight;
		if(isWithinTolerance(Height.CLIMB)) {
			targetHeight = Height.SCALE;
		} else if(isWithinTolerance(Height.SCALE)) {
			targetHeight = Height.SWITCH;
		} else if(isWithinTolerance(Height.SWITCH)) {
			targetHeight = Height.GROUND;
		} else {
			return null;
		}
		return new ElevatorCommand(targetHeight);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
}

