package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	

	/*public boolean elevatorHeight(Height height) {
		if(targetHeightIndex != height) {
			targetHeightIndex = height;
		}
		return true;
	}*/
	
	public double currentHeight() {
		
		double lowerHeight = lowerMotor1.getSelectedSensorPosition(0);
		double upperHeight = upperMotor.getSelectedSensorPosition(0);
		
		double totalHeight = lowerHeight+upperHeight;
		
		return totalHeight;
	}
	
	public boolean isFinished(){
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
    	
    	lowerMotor1.configMotionAcceleration(100, 0);
    	upperMotor.configMotionAcceleration(100, 0);
    	
    	lowerMotor1.configMotionCruiseVelocity(100, 0);
    	upperMotor.configMotionCruiseVelocity(100, 0);
	}
	// No longer has fake values <3, this just sets the values for the ground, switch, scale, and climb heights that the elevator uses.
	public static enum Height {
		GROUND(RobotMap.ELEVATOR_GROUND_HEIGHT), SWITCH(RobotMap.ELEVATOR_SWITCH_HEIGHT), SCALE(RobotMap.ELEVATOR_SCALE_HEIGHT), CLIMB(RobotMap.ELEVATOR_CLIMB_HEIGHT);
		
		public int height;
		private Height(int height) {
			this.height = height;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
}

