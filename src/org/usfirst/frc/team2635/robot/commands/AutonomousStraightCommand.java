package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousStraightCommand extends Command {

    public AutonomousStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drive.frontLeftMotor.set(ControlMode.MotionMagic, 3000);
    	//Robot.drive.frontRightMotor.set(ControlMode.MotionMagic, -3000);
    	double distance = Robot.drive.inchesToCounts(RobotMap.INCHES);
    	Robot.drive.motorControl(ControlMode.MotionMagic, distance, -distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Checks a zone around the position and finishes command if inside both
    	if(Robot.drive.getFrontLeftPos() <= 3005 && Robot.drive.getFrontLeftPos() >= 2995 && Robot.drive.getFrontRightPos() >= -3005 && Robot.drive.getFrontRightPos() <= -2995){
    		System.out.println("Auto Finished");
    		return true;
    	}
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
