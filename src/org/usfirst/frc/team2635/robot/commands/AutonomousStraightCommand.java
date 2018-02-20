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
public class AutonomousStraightCommand extends Command {
	MotionParameters motionParams;
	double distance;
	double velocity;
	double acceleration;
	double intialHeading; 
    public AutonomousStraightCommand(double distance, double velocity, double acceleration) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        this.distance = distance;
        this.velocity = velocity;
        this.acceleration = acceleration;
        
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
    	Robot.drive.navxReset();
    	motionParams = MotionMagicLibrary.getDriveParameters(RobotMap.WHEEL_RADIUS_INCHES, distance, velocity, false, acceleration);
    	
    	intialHeading = Robot.drive.getNavxHeading();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drive.frontLeftMotor.set(ControlMode.MotionMagic, 3000);
    	//Robot.drive.frontRightMotor.set(ControlMode.MotionMagic, -3000);
    	
    	
    	Robot.drive.motionMagic(motionParams);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Checks a zone around the position and finishes command if inside both
    	
    	boolean isFinished = Robot.drive.motionMagicDone(motionParams, RobotMap.ERRORTOLERANCE);
    	if(isFinished) {
    		double currentHeading = Robot.drive.getNavxHeading();
    		double navxHeading = Math.abs(intialHeading-currentHeading);
    		System.out.println("Navx straight heading:" + navxHeading);
    		System.out.println("Navx straight-drive angle: " + Robot.drive.getNavxAngle());
    		System.out.println("Drive Straight Finished.");
    		System.out.println("-----------");
    	}
    	
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.motorControl(ControlMode.PercentOutput, 0.0, 0.0, false);
    	Robot.drive.reset();
    	Robot.drive.navxReset();
    	
//    	System.out.println("Drive straight end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	System.out.println("Drive straight interrupted");
    	end();
    }
}
