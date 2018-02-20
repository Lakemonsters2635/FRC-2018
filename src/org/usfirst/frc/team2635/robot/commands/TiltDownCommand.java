package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TiltDownCommand extends TimedCommand {

    public TiltDownCommand(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(timeout);
    	requires(Robot.tilter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("TiltDownCommand.Initialize" );
    	Robot.tilter.setDown();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    	System.out.println("TiltDownCommand.end" );
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
