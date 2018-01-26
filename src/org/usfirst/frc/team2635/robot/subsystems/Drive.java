package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionParameters;
import org.usfirst.frc.team2635.robot.model.Navx;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends Subsystem {	
	WPI_TalonSRX frontLeftMotor;
	WPI_TalonSRX frontRightMotor;
	WPI_TalonSRX backLeftMotor;
	WPI_TalonSRX backRightMotor;
	Joystick leftJoy;
	Joystick rightJoy;
	DifferentialDrive drive;
    
	public Drive(){
		//2017 BUNNYBOT
//		frontLeftMotor = new WPI_TalonSRX(1);
//		frontRightMotor = new WPI_TalonSRX(3);
//		backLeftMotor = new WPI_TalonSRX(2);
//		backRightMotor = new WPI_TalonSRX(4);
		//2017 BOT
		frontLeftMotor = new WPI_TalonSRX(3);
		frontRightMotor = new WPI_TalonSRX(1);
		backLeftMotor = new WPI_TalonSRX(4);
		backRightMotor = new WPI_TalonSRX(2);
		
		
		//Polarity of Encoder
		frontLeftMotor.setSensorPhase(true);
		frontRightMotor.setSensorPhase(true);
		//END 2017 BOT
		
		
		
	}

	public void tankDrive(double left, double right){
		double absleft = Math.abs(left);
		double absright = Math.abs(right);
		if(absleft<0.05) left = 0;
		if(absright<0.05) right = 0;
		if(RobotMap.VELOCITYDRIVEMODE){
			frontLeftMotor.setSelectedSensorPosition(0, 0, 0);
			frontLeftMotor.config_kP(1, RobotMap.MOTION_MAGIC_P, 0);
			frontLeftMotor.config_kI(1, 0, 0);
			frontLeftMotor.config_kD(1, RobotMap.MOTION_MAGIC_D, 0);
			frontLeftMotor.config_kF(1, RobotMap.MOTION_MAGIC_F, 0);
    	
			frontRightMotor.setSelectedSensorPosition(0, 0, 0);
			frontRightMotor.config_kP(1, RobotMap.MOTION_MAGIC_P, 0);
			frontRightMotor.config_kI(1, 0, 0);
			frontRightMotor.config_kD(1, RobotMap.MOTION_MAGIC_D, 0);
			frontRightMotor.config_kF(1, RobotMap.MOTION_MAGIC_F, 0);
			
	    	frontRightMotor.selectProfileSlot(1, 0);
	    	frontLeftMotor.selectProfileSlot(1, 0);
	    	
			motorControl(ControlMode.Velocity, -left*1000, right*1000, true);
		}else {
			motorControl(ControlMode.PercentOutput, -left, right, false);
		}
	}
	
	public void motorControl(ControlMode controlMode, Double left, Double right, Boolean slave){
		frontLeftMotor.set(controlMode, left);
		frontRightMotor.set(controlMode, right);
		if(!slave){
			backRightMotor.set(controlMode, right);
			backLeftMotor.set(controlMode, left);
		}else {
			//back eg. slaves
			backLeftMotor.follow(frontLeftMotor);
			backRightMotor.follow(frontRightMotor);
		}
		
	}
    
    public void autoInit() {
    	
    	frontLeftMotor.setSelectedSensorPosition(0, 0, 0);
    	frontLeftMotor.config_kP(0, RobotMap.MOTION_MAGIC_P, 0);
    	frontLeftMotor.config_kI(0, RobotMap.MOTION_MAGIC_I, 0);
    	frontLeftMotor.config_kD(0, RobotMap.MOTION_MAGIC_D, 0);
    	frontLeftMotor.config_kF(0, RobotMap.MOTION_MAGIC_F, 0);
    	
    	frontRightMotor.setSelectedSensorPosition(0, 0, 0);
    	frontRightMotor.config_kP(0, RobotMap.MOTION_MAGIC_P, 0);
    	frontRightMotor.config_kI(0, RobotMap.MOTION_MAGIC_I, 0);
    	frontRightMotor.config_kD(0, RobotMap.MOTION_MAGIC_D, 0);
    	frontRightMotor.config_kF(0, RobotMap.MOTION_MAGIC_F, 0);
    	frontRightMotor.selectProfileSlot(0, 0);
    	frontLeftMotor.selectProfileSlot(0, 0);
    	//Backup in case values are not otherwise set
    	frontRightMotor.configMotionAcceleration(RobotMap.MOTION_MAGIC_ACCELERATION, 0);
    	frontLeftMotor.configMotionAcceleration(RobotMap.MOTION_MAGIC_ACCELERATION, 0);
    	
    	frontRightMotor.configMotionCruiseVelocity(RobotMap.MOTION_MAGIC_CRUISE_VELOCITY, 0);
    	frontLeftMotor.configMotionCruiseVelocity(RobotMap.MOTION_MAGIC_CRUISE_VELOCITY, 0);
    	//End Backup
    	
    }
    
    public int getFrontRightPos(){
    	return frontRightMotor.getSelectedSensorPosition(0);
    }
    public int getFrontLeftPos(){
    	return frontLeftMotor.getSelectedSensorPosition(0);
    }
    public double inchesToCounts(double inches){
    	double circumference = RobotMap.WHEEL_DIAMETER * Math.PI;
    	
    	double counts = inches/circumference*1000;
    	return counts;
    }
    public void motionMagic(MotionParameters motionParams) {
		frontRightMotor.configMotionCruiseVelocity(motionParams.rightVelocity, 0);
		frontLeftMotor.configMotionCruiseVelocity(motionParams.leftVelocity, 0);

		frontRightMotor.configMotionAcceleration(motionParams.rightAcceleration, 0);
		frontLeftMotor.configMotionAcceleration(motionParams.leftAcceleration, 0);
		
		motorControl(ControlMode.MotionMagic, motionParams.leftWheelRotations, motionParams.rightWheelRotations, true);
		
	

	}
    
    public boolean motionMagicDone(MotionParameters motionParams, double errorTolerance) {
    	
    	double leftIntended = motionParams.leftWheelRotations;
    	double rightIntended = motionParams.rightWheelRotations;
    	
    	double leftPos = Robot.drive.frontLeftMotor.getSelectedSensorPosition(0);
    	double rightPos = Robot.drive.frontRightMotor.getSelectedSensorPosition(0);
    	
    	double leftError = Math.abs(leftIntended - leftPos);
    	double rightError = Math.abs(rightIntended - rightPos);
    	
    	
    	System.out.println("Left Error: " + leftError + "    Right Error: " + rightError);
    	if(leftError < errorTolerance && rightError < errorTolerance) {
    		
    		return true;
    	}
    	
    	return false;
    }
    public void reset(){
    	frontLeftMotor.setSelectedSensorPosition(0, 0, 0);
    	frontRightMotor.setSelectedSensorPosition(0, 0, 0);
    	
    	frontLeftMotor.set(ControlMode.PercentOutput, 0);
    	frontRightMotor.set(ControlMode.PercentOutput, 0);
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

