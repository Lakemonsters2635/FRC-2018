package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

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
		frontLeftMotor = new WPI_TalonSRX(1);
		frontRightMotor = new WPI_TalonSRX(3);
		backLeftMotor = new WPI_TalonSRX(2);
		backRightMotor = new WPI_TalonSRX(4);
		
		backLeftMotor.follow(frontLeftMotor);
		backRightMotor.follow(frontRightMotor);
		
		
		
		
	}
	
		

	public void tankDrive(double left, double right){
		
		motorControl(ControlMode.PercentOutput, left, -right);
	}
	public void motorControl(ControlMode controlMode, Double left, Double right){
		frontLeftMotor.set(controlMode, left);
		frontRightMotor.set(controlMode, right);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    	
    	frontRightMotor.configMotionAcceleration(250, 0);
    	frontLeftMotor.configMotionAcceleration(250, 0);
    	
    	frontRightMotor.configMotionCruiseVelocity(250, 0);
    	frontLeftMotor.configMotionCruiseVelocity(250, 0);
    	
//      	frontLeftMotor.set(ControlMode.MotionMagic, 3000);
//    	frontRightMotor.set(ControlMode.MotionMagic, -3000);
    	
    }
}

