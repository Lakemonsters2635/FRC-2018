package org.usfirst.frc.team2635.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FMS extends Subsystem {
	DriverStation driveStation;
	
	public FMS () {
		driveStation = DriverStation.getInstance();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public String getGameMessage() {
    	
    	try{
    		String message = driveStation.getGameSpecificMessage();
    		System.out.println(message);
    		return message;
    	} catch(Exception e){
    		System.out.println("Game Message Failure: "+e);
    		return "Game Message Failure: "+e;
    	}
    }
    
    public String getAllianceString() {
    	//ds.getLocation()
    	//ds.getAlliance()
    	//ds.getGameSpecificMessage();
    	//ds.waitForData();
    	
    	
    	
    	Alliance message = driveStation.getAlliance();
    	System.out.println(message);
    	return message.toString();
    }
    
    public Alliance getAlliance() {
    	Alliance message = driveStation.getAlliance();
    	System.out.println(message);
    	return message;
    }
    
    public String getEvent() {
    	String message = driveStation.getEventName();
    	System.out.println(message);
    	return message;
    }
    
    public Integer getMatchNumber() {
    	Integer message = driveStation.getMatchNumber();
    	System.out.println(message);
    	return message;
    }
    
    public Double getMatchTime() {
    	Double message = driveStation.getMatchTime();
    	System.out.println(message);
    	return message;
    }
    
    public String getMatchTypeString() {
    	MatchType message = driveStation.getMatchType();
    	System.out.println(message);
    	return message.toString();
    }
    
    public String getMatchType() {
    	MatchType message = driveStation.getMatchType();
    	System.out.println(message);
    	return message.toString();
    }
    
    public int getLocation() {
    	int message = driveStation.getLocation();
    	System.out.println(message);
    	return message;
    }
    
}
