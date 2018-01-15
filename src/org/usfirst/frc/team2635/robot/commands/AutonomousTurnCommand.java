package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.model.MotionParameters;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousTurnCommand extends Command {

	MotionParameters rotationParams;
	
	double rpm;
	double targetAngle;
	
    public AutonomousTurnCommand(double rpm, double targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.rpm = rpm;
    	this.targetAngle = targetAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.SetNavxAngle();
    	
    	targetAngle = 90;
	   	rotationParams = MotionMagicLibrary.getRotationParameters(90,
				RobotMap.WHEEL_RADIUS_INCHES, RobotMap.WHEEL_SEPARATION_INCHES, rpm);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.drive.rotateMotionMagic(rotationParams);  
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
