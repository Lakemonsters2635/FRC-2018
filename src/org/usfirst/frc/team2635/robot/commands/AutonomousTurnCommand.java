package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.model.MotionParameters;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousTurnCommand extends Command {

	MotionParameters rotationParams;
	
	double rpm;
	double targetAngle;
	double acceleration;
	boolean encodersDone;
	double errorTolerance;
	int retryCount = 0;
	
    public AutonomousTurnCommand(double rpm, double targetAngle, double acceleration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.rpm = rpm;
    	this.targetAngle = targetAngle;
    	this.acceleration = acceleration;
    	errorTolerance = RobotMap.ERRORTOLERANCE;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.drive.reset();
    	Robot.drive.navxReset();
    	double navxAngle = Robot.drive.getNavxAngle();
	   	rotationParams = MotionMagicLibrary.getRotationParameters(targetAngle,
				RobotMap.WHEEL_RADIUS_INCHES, RobotMap.WHEEL_SEPARATION_INCHES, rpm, acceleration);
	   	encodersDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   
    	if(!encodersDone) {
    		Robot.drive.motionMagic(rotationParams);  
    		encodersDone = Robot.drive.motionMagicDone(rotationParams, errorTolerance);
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//MotionParameters motionParams, double targetAngle, double encoderErrorTolerance, double navxErrorTolerance
    	double navxErrorTolerance = 0.3; //TODO: Put in RobotMap
    	//boolean done = Robot.drive.rotationDone(rotationParams, targetAngle, RobotMap.ERRORTOLERANCE, navxErrorTolerance);
    	
    	boolean encodersDone = Robot.drive.motionMagicDone(rotationParams,errorTolerance);

    	
    	if(encodersDone) {
//    		System.out.println("Navx turn angle: " + Robot.drive.getNavxAngle());
//        	double navxAngle = Robot.drive.getNavxAngle();
//        	double angleDelta = (-targetAngle - navxAngle);
//        	boolean navxDone = ( Math.abs(angleDelta) < navxErrorTolerance);
//        	
//        	if (!navxDone && retryCount == 0) {
//        		retryCount++;
//        		
//        		Robot.drive.setPIDValues(100);
//        		System.out.println("TRY AGAIN!!!");
//        		System.out.println("Retry Navx turn angle: " + navxAngle);
//        		System.out.println("angleDelta: " + angleDelta);
//        		encodersDone = false;
//        		targetAngle = angleDelta;
//        		this.initialize(); 
//        	}
    		
    	}
    	
    	if (encodersDone) {
        	double navxAngle = Robot.drive.getNavxAngle();
        	double angleDelta = (-targetAngle - navxAngle);
        	System.out.println("Final Navx turn delta: " + angleDelta);
        	System.out.println("Final Navx turn angle: " + navxAngle);
    		System.out.println("Drive Turn Finished");
    		System.out.println("-----------");
    		Robot.drive.setPIDValues(RobotMap.MOTION_MAGIC_P);
    	}
    	return encodersDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setPIDValues(RobotMap.MOTION_MAGIC_P);
    	Robot.drive.motorControl(ControlMode.PercentOutput, 0.0, 0.0, false);
    	Robot.drive.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    }
}
