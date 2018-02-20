package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class GrabberOpen extends TimedCommand {

    public GrabberOpen(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(timeout);
    	requires(Robot.grabber);
    	
    }



    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grabber.setOpen();
    	System.out.println("GrabberOpen.initialize" );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.grabber.setOpen();
    }


    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("GrabberOpen.end" );
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	    }
    

}
