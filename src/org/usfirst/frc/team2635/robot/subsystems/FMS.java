package org.usfirst.frc.team2635.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FMS extends Subsystem {
	DriverStation ds;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public String getGameMessage() {
    	try{
    		String message = ds.getGameSpecificMessage();
    		System.out.println(message);
    		return message;
    	} catch(Exception e){
    		System.out.println("Game Message Failure: "+e);
    		return "Game Message Failure: "+e;
    	}
    }
    
    public String getAllianceString() {
    	Alliance message = ds.getAlliance();
    	System.out.println(message);
    	return message.toString();
    }
    
    public Alliance getAlliance() {
    	Alliance message = ds.getAlliance();
    	System.out.println(message);
    	return message;
    }
    
    public String getEvent() {
    	String message = ds.getEventName();
    	System.out.println(message);
    	return message;
    }
    
    public Integer getMatchNumber() {
    	Integer message = ds.getMatchNumber();
    	System.out.println(message);
    	return message;
    }
    
    public Double getMatchTime() {
    	Double message = ds.getMatchTime();
    	System.out.println(message);
    	return message;
    }
    
    public String getMatchTypeString() {
    	MatchType message = ds.getMatchType();
    	System.out.println(message);
    	return message.toString();
    }
    
    public String getMatchType() {
    	MatchType message = ds.getMatchType();
    	System.out.println(message);
    	return message.toString();
    }
    
    public int getLocation() {
    	int message = ds.getLocation();
    	System.out.println(message);
    	return message;
    }
    
}
