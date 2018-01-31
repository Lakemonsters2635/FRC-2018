package org.usfirst.frc.team2635.robot.model;

import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class FMSInfo {
	
	public Alliance alliance;
	public int driveStation;
	public boolean isAutonomous;
	public boolean isInitalized;
	public char switchLocation; //What we're going to parse first, first char in the game message
	public char scaleLocation; //The 2nd char in the game message
	public char opponentSwitchLocation; //3rd char in the game message

}
