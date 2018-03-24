package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.model.MotionParameters;

import java.security.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTurnCommand extends Command {

	MotionParameters rotationParams;
	
	double rpm;
	double targetAngle;
	double acceleration;
	boolean encodersDone;
	double initialAngle;
	int retryCount = 0;
	
	
    public AutonomousTurnCommand(double rpm, double targetAngle, double acceleration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	//FHE: WARNING: HARD CODED TIME OUT
    	
    	this.setTimeout(3);
    	this.rpm = rpm;
    	this.targetAngle = targetAngle;
    	this.acceleration = acceleration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
	   	encodersDone = false;

    	//this.initialAngle = Robot.drive.getNavxAngle();
    	double currentAngle = Robot.drive.getNavxAngle(); 
    	double deltaAngle = 0.0; 
    	//double adjustedTargetAngle = targetAngle - deltaAngle;
    	double adjustedTargetAngle = targetAngle;
    	boolean isClockWiseTurn = (targetAngle < 0.0);
    	boolean targetAngleIs90 = (targetAngle % 90.0 == 0.0);
     	
    	System.out.println("-----Autonomous Turn Started----");
    	if (targetAngleIs90)
    	{
    		deltaAngle = currentAngle % 90.0;
        	if (Math.abs(deltaAngle) > 75)
        	{
//        		if (deltaAngle > 75)
//        		{
//        			deltaAngle = 90 - deltaAngle;
//        		}
//        		else if (deltaAngle < -75)
//        		{
//        			deltaAngle = 90 + deltaAngle;
//        		}
        			
            	System.out.println("Delta Angle is WACKY!!!!, so fixing: "+ deltaAngle);
            	deltaAngle =  90.0 % deltaAngle;
            	//adjustedTargetAngle = targetAngle;
            	adjustedTargetAngle = deltaAngle;
            	System.out.println("Delta Angle fixed: "+ deltaAngle);
        	} 		
        	System.out.println("Right Angle Delta: "+ deltaAngle );
    	   	adjustedTargetAngle = targetAngle + deltaAngle;
    	}
    	

    	//Starting Heading
    	//Heading at start of Turn =-92
    	// 

    	 
    	//Robot.drive.navxReset();
    	//double navxAngle = Robot.drive.getNavxAngle();

    	System.out.println("current Angle: " + currentAngle + " Target Angle: "+ targetAngle + "   Delta: " + deltaAngle +  "   Adjusted Target Angle:" + adjustedTargetAngle);
    	
    	
    	
	   	rotationParams = MotionMagicLibrary.getRotationParameters(targetAngle,
				RobotMap.WHEEL_RADIUS_INCHES, RobotMap.WHEEL_SEPARATION_INCHES, rpm, acceleration);
	   

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   
    	if(!encodersDone) {
    		Robot.drive.motionMagicRotate(rotationParams);  
    		encodersDone = Robot.drive.motionMagicDone(rotationParams, RobotMap.ROTATE_ERRORTOLERANCE, false, false);
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//MotionParameters motionParams, double targetAngle, double encoderErrorTolerance, double navxErrorTolerance
    	//boolean done = Robot.drive.rotationDone(rotationParams, targetAngle, RobotMap.ERRORTOLERANCE, navxErrorTolerance);
    	boolean isTurnFinished = isTimedOut();
    	
    	
    	if (!isTurnFinished) {
    		isTurnFinished = Robot.drive.motionMagicDone(rotationParams,RobotMap.ROTATE_ERRORTOLERANCE, false, false);
    	} else {
    		System.out.println("Turn timed out");
    	}
    	
   	
    	if (isTurnFinished) {
    		System.out.println("Drive Turn Finished");
    		System.out.println("-----------");
    		Robot.drive.setPIDValues(RobotMap.MOTION_MAGIC_P);

    	}
    	return isTurnFinished;
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
