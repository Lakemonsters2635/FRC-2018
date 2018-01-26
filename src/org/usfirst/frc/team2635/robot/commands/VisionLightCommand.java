package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionLightCommand extends Command {

    public VisionLightCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vision.ledOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vision.ledOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.vision.ledOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.vision.ledOff();
    }
}
