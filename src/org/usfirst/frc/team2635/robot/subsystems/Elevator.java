package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2635.robot.commands.EmptyCommand;
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
	WPI_TalonSRX smallMotor;
	WPI_TalonSRX largeMotor1; 
	WPI_TalonSRX largeMotor2; 
	
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
	
	public Height currentTargetHeight;
	double largeElevatorMax;
	double smallElevatorMax;
	ElevatorCommand elevatorCommand;
	
	public Elevator() {
		smallMotor  = new WPI_TalonSRX(RobotMap.ELEVATOR_UPPER_MOTOR_CHANNEL);
    	largeMotor1 = new WPI_TalonSRX(RobotMap.ELEVATOR_LOWER_MOTOR1_CHANNEL);
    	largeMotor2 = new WPI_TalonSRX(RobotMap.ELEVATOR_LOWER_MOTOR2_CHANNEL);
    	
    	//bottomLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_LIMIT_SWITCH_IO_CHANNEL); 
    	//topLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_TOP_LIMIT_SWITCH_IO_CHANNEL); 

    	
    	largeMotor2.follow(largeMotor1);
    	encoderStart();
    	//TODO FAKE VALUES, UPDATE TO ACCURATE VALUES, Fix this...
    	// In inches (units)
    	
    	largeElevatorMax = 17500;
    	smallElevatorMax = 20000;
    	//END FAKE VALUES
    	double theCurrentHeight = currentHeight();
    	
    	elevatorCommand = new ElevatorCommand(Height.GROUND);
    	largeMotor1.setSensorPhase(true);
    	if (isWithinTolerance(Height.GROUND)) {
    		currentTargetHeight = Height.GROUND;
    		System.out.println("Initializing target Height to Ground.");
    	
    	}
    	else {
    		currentTargetHeight = Height.GROUND;
    		System.out.println("WARNING: Current height NOT near ground!!!!. Current Height:" + theCurrentHeight);
    		
    	}
    	
	}
	
	public void motorControl() {
	System.out.println(currentTargetHeight.toString());
		double lowerHeight = 0;
		double upperHeight = 0;
		
		if(currentTargetHeight.height > smallElevatorMax) {
			lowerHeight = smallElevatorMax;
			upperHeight = currentTargetHeight.height - smallElevatorMax;
			//System.out.println("currentTargetHeight.height > smallElevatorMax");
			
			//System.out.println("Upper height: "+ upperHeight);
			//System.out.println("Lower height: "+ lowerHeight);
		} else {
			upperHeight = 0;
			lowerHeight = currentTargetHeight.height;
			//System.out.println("currentTargetHeight.height < smallElevatorMax");
			
			//System.out.println("Upper height: "+ upperHeight);
			//System.out.println("Lower height: "+ lowerHeight);
		}

		if (currentTargetHeight == Height.GROUND) {
			largeMotor1.set(ControlMode.PercentOutput, 0);
			smallMotor.set(ControlMode.PercentOutput, 0);
			
			
		}
		else {
			largeMotor1.set(ControlMode.MotionMagic, upperHeight);
			smallMotor.set(ControlMode.MotionMagic, lowerHeight);
			
		}
		

	}
	
	public void limitSwitchEncoderReset() {
		//boolean upperLimitSwitchPressed = isLimitSwitchPressed(topLimitSwitch);
		//boolean lowerLimitSwitchPressed = isLimitSwitchPressed(topLimitSwitch);
		
//		if(upperLimitSwitchPressed) {
//			upperMotor.setSelectedSensorPosition(0, 0, 0);
//		}
//		
//		if(lowerLimitSwitchPressed) {
//			lowerMotor1.setSelectedSensorPosition(0, 0, 0);
//		}
	}
	public boolean setTargetHeight(Height height) {
		elevatorCommand.height = height;
		currentTargetHeight = height;
		return true;
	}
	
	
	public double currentHeight() {
		
		double lowerHeight = largeMotor1.getSelectedSensorPosition(0);
		double upperHeight = smallMotor.getSelectedSensorPosition(0);
		
		double totalHeight = lowerHeight+upperHeight;
		System.out.println("lowerHeight:" + lowerHeight + "\tupperHeight:" + upperHeight + "\t totalHeight:" + totalHeight);
		return totalHeight;
	}
	public boolean isLimitSwitchPressed(DigitalInput limitSwitch) {
		boolean result = false;
		try {
			result = !limitSwitch.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
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
		
    	
    	double upperEncoderPosition = smallMotor.getSelectedSensorPosition(0);
    	double lowerEncoderPosition = largeMotor1.getSelectedSensorPosition(1);
		return false;
	}
	
	public boolean isWithinTolerance(Height height) {
		return (currentHeight() > (height.height - RobotMap.ELEVATOR_TOLERANCE) 
			  && currentHeight() < (height.height + RobotMap.ELEVATOR_TOLERANCE)); 
	}
	
	public void encoderStart() {
		
		System.out.println("encoderStart()");
		smallMotor.setSelectedSensorPosition(0, 0, 0);
    	smallMotor.config_kP(0, 5, 0);
    	smallMotor.config_kI(0, 0, 0);
    	smallMotor.config_kD(0, 0, 0);
    	smallMotor.config_kF(0, 0, 0);
    	
    	largeMotor1.setSelectedSensorPosition(0, 0, 0);
    	largeMotor1.config_kP(0, 5, 0);
    	largeMotor1.config_kI(0, 0, 0);
    	largeMotor1.config_kD(0, 0, 0);
    	largeMotor1.config_kF(0, 0, 0);
    	
    	
    	largeMotor1.selectProfileSlot(0, 0);
    	smallMotor.selectProfileSlot(0, 0);
    	
    	largeMotor1.configMotionAcceleration(RobotMap.ELEVATOR_ACCELERATION, 0);
    	smallMotor.configMotionAcceleration(RobotMap.ELEVATOR_ACCELERATION, 0);
    	
    	largeMotor1.configMotionCruiseVelocity(RobotMap.ELEVATOR_VELOCITY, 0);
    	smallMotor.configMotionCruiseVelocity(RobotMap.ELEVATOR_VELOCITY, 0);
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
	
	//Not Used
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
	
	public Command ElevatorUp() {
		Height newTargetHeight;
		switch(this.currentTargetHeight) {
		case GROUND:
			 setTargetHeight(Height.SWITCH);
			 break;
		case SWITCH:
			setTargetHeight(Height.SCALE);
	         break;
		case SCALE:
			setTargetHeight(Height.CLIMB);
	         break;
		case CLIMB:
			setTargetHeight(Height.CLIMB);
	    default:
	        return null;
	     
		}
		
		return elevatorCommand;
		
		 // Raises the elevator to the next level if its w/i a defined tolerance.
	}
	
//	public  Command ElevatorMove(Height height) {
//
//		 return new ElevatorCommand(height);
//	}
	//pointless ^^^
	
	public  Command ElevatorDown(){
		Height newTargetHeight;
		switch(this.currentTargetHeight) {
		case CLIMB:
			setTargetHeight(Height.SCALE);
			 break;
		case SCALE:
			setTargetHeight(Height.SWITCH);
	         break;
		case SWITCH:
			setTargetHeight(Height.GROUND);
	         break;
		case GROUND:
			setTargetHeight(Height.GROUND);
			 break;
	    default:
	        return null;
		}
		return elevatorCommand;
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
}

