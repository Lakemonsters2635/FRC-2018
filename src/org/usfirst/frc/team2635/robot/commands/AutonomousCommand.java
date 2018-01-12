package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousCommand extends Command {

    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.drive.autoInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drive.frontLeftMotor.set(ControlMode.MotionMagic, 3000);
    	//Robot.drive.frontRightMotor.set(ControlMode.MotionMagic, -3000);
    	Robot.drive.motorControl(ControlMode.MotionMagic, 3000.0, -3000.0);
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
