package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TiltUpCommand extends TimedCommand {

    public TiltUpCommand(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(timeout);
    	requires(Robot.tilter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.tilter.setUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
