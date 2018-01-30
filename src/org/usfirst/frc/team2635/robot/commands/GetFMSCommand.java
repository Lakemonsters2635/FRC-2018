package org.usfirst.frc.team2635.robot.commands;

import java.util.Random;

import org.usfirst.frc.team2635.robot.model.FMSInfo;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetFMSCommand extends Command {

	public FMSInfo fmsInfo;
	DriverStation driveStation;
	Random fmsRandom; 
    public GetFMSCommand(FMSInfo fmsInfo) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.fmsInfo = fmsInfo;
    	fmsRandom = new Random();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveStation = DriverStation.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//FHE: What does this do?
    	String gameSpecificMessage = "";

    		
    		driveStation.waitForData();
        	fmsInfo.alliance = driveStation.getAlliance();
        	gameSpecificMessage = driveStation.getGameSpecificMessage();

    		fmsInfo.switchLocation = String.valueOf(gameSpecificMessage.charAt(0));
        	fmsInfo.scaleLocation = String.valueOf(gameSpecificMessage.charAt(1));
        	fmsInfo.opponentSwitchLocation = String.valueOf(gameSpecificMessage.charAt(2));
        	fmsInfo.driveStation = driveStation.getLocation();
        	fmsInfo.isAutonomous = driveStation.isAutonomous();
        	fmsInfo.isInitalized = true;
    
    		
    	}
    	
    	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return fmsInfo.isInitalized;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Fms DriveStation: " + fmsInfo.driveStation);
    	System.out.println("Fms alliance: " + fmsInfo.alliance);
    	System.out.println("Fms isAutonomous: " + fmsInfo.isAutonomous);
    	System.out.println("Switch Location: " + fmsInfo.switchLocation);
    	System.out.println("Scale Location: " + fmsInfo.scaleLocation);
    	System.out.println("Opponent Scale Location: " + fmsInfo.opponentSwitchLocation);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
