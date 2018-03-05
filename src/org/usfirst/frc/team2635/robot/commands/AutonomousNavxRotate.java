package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.model.MotionParameters;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousNavxRotate extends Command {
	MotionParameters rotationParams;

	double rpm;
	double targetAngle;
	double acceleration;
	
    public AutonomousNavxRotate(double rpm, double targetAngle, double acceleration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.rpm = rpm;
    	this.targetAngle = targetAngle;
    	this.acceleration = acceleration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.navxReset();
    	Robot.drive.reset();
    	rotationParams = MotionMagicLibrary.getRotationParameters(targetAngle,
				RobotMap.WHEEL_RADIUS_INCHES, RobotMap.WHEEL_SEPARATION_INCHES, rpm, acceleration);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.motionMagicRotate(rotationParams);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double navxErrorTolerance = 1; //TODO: Put in RobotMap
    	boolean isFinished = Robot.drive.rotationDone(rotationParams, targetAngle, RobotMap.ERRORTOLERANCE, navxErrorTolerance);
    	if(isFinished) {
    		System.out.println("Drive Navx Turn Finished");
    		System.out.println("-----------");
    	}
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
