package org.usfirst.frc.team2635.robot.model;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.AutonomousNavxRotate;
import org.usfirst.frc.team2635.robot.commands.AutonomousStraightCommand;
import org.usfirst.frc.team2635.robot.commands.AutonomousTurnCommand;
import org.usfirst.frc.team2635.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2635.robot.commands.GrabberCommand;
import org.usfirst.frc.team2635.robot.commands.TiltCommand;
import org.usfirst.frc.team2635.robot.subsystems.Elevator.Height;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

public class MotionMagicLibrary
{


	
	
	public static MotionParameters getRotationParameters(double targetAngle,    double wheelRadiusInches, 
			double wheelSeparationInches,  double velocity, double acceleration)	
	{
			double inchesPerRotation = wheelRadiusInches * 2 * Math.PI;
			System.out.println("Rotation Parameters Called");
			double arcLengthRight;
			double archLengthLeft;
			double rightWheelRotations;
			double leftWheelRotations;
			
			
			//To rotate around center.
			double radius = wheelSeparationInches/2.0;
			//radius is 1/2 of wheelSeparationInches
			//ArcLengh = radius * angle in radians
			
			arcLengthRight = radius *  (2*Math.PI)/360.0 * targetAngle;
			archLengthLeft = arcLengthRight;
			rightWheelRotations = -arcLengthRight/inchesPerRotation;
			leftWheelRotations = -archLengthLeft/inchesPerRotation;
			
		
				
			
			double velocityRatio = Math.abs(leftWheelRotations/rightWheelRotations);
			
			double rightVelocity = velocity;
			double leftVelocity = velocity * velocityRatio;
			
			double rightAcceleration =  acceleration;
			double leftAcceleration =  acceleration;
		
			
			
			MotionParameters rotationParams = new MotionParameters();
			rotationParams.rightAcceleration = (int) rightAcceleration;
			rotationParams.leftAcceleration = (int) leftAcceleration;
			rotationParams.rightVelocity     = (int) rightVelocity;
			rotationParams.leftVelocity     = (int) leftVelocity;
			rotationParams.rightWheelRotations = rightWheelRotations*RobotMap.ENCODER_COUNTS_PER_REVOLUTION;
			rotationParams.leftWheelRotations = leftWheelRotations*RobotMap.ENCODER_COUNTS_PER_REVOLUTION;
			
			return rotationParams;
			}

	
	public static MotionParameters getDriveParameters(double wheelRadiusInches, double distanceInches, double velocity, boolean reverse, double acceleration)
	{
		

		double inchesPerRotation = wheelRadiusInches * 2 * Math.PI;
		
		//double arcLengthInner;
		//double archLengthOuter;
		double velocit = velocity;
		
		
		//FOR COMPETITION BOT DO THE FOLLOWING
		double leftWheelRotations = distanceInches/inchesPerRotation;
		//END COMPETITION BOT
		
		double rightWheelRotations = -distanceInches/inchesPerRotation;
		
		if (reverse)
		{			
			leftWheelRotations = -leftWheelRotations;
			rightWheelRotations = -rightWheelRotations;
		}
		
		MotionParameters driveParams = new MotionParameters();
		driveParams.leftAcceleration = (int) acceleration;
		driveParams.rightAcceleration = (int) acceleration;
		driveParams.leftWheelRotations = leftWheelRotations * RobotMap.ENCODER_COUNTS_PER_REVOLUTION;
		driveParams.rightWheelRotations = rightWheelRotations * RobotMap.ENCODER_COUNTS_PER_REVOLUTION;
		driveParams.leftVelocity     = (int) velocit;
		driveParams.rightVelocity     = (int) velocit;
		return driveParams;

	}
	
	public static CommandGroup RightStationToSwitch()
	{
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = getFMSInfo();
		if (fmsInfo.switchLocation == 'R')
		{
			output = RightStationToRightSwitch();
		}
		else if (fmsInfo.switchLocation == 'L') {
			output = RightStationToLeftSwitch();
		}
		else
		{
			output = CrossLineCommand();
		}
		output.setName(getMethodName());
		
		return output;
	}
	
	public  static CommandGroup CenterStationToSwitch()
	{
		CommandGroup output = new CommandGroup();
		
		FMSInfo fmsInfo = getFMSInfo();

		if (fmsInfo.switchLocation == 'R')
		{
			output = CenterStationToRightSwitch();
		}
		else if (fmsInfo.switchLocation == 'L')
		{
			output = CenterStationToLeftSwitch();
		}
		else
		{
			output = CrossLineCommand();
		}
		output.setName(getMethodName());
		return output;
	}
	
	public  static CommandGroup LeftStationToSwitch()
	{
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = getFMSInfo();
		
		if (fmsInfo.switchLocation == 'R')
		{
			output = LeftStationToRightSwitch();
		}
		else if (fmsInfo.switchLocation == 'L'){
			output = LeftStationToLeftSwitch();
		}
		else
		{
			output = CrossLineCommand(); //Turn into cross line
		}
		
		output.setName(getMethodName());
		return output;
	}
	
	public  static CommandGroup FarLeftToScale()
	{
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R')
		{
			output = LeftToRightScale();
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = LeftToLeftScale();
		}
		else
		{
			output = CrossLineCommand();
		}
		output.setName(getMethodName());
		return output;
	}
	
	
	public static CommandGroup FarLeftToSwitch(){
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R')
		{
			//output = FarLeftToRightScale();
			
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = FarLeftToLeftScale();
		}
		else
		{
			output = CrossLineCommand();
		}
		output.setName(getMethodName());
		return output;
	}
	
	
	private static CommandGroup FarLeftToLeftScale() {
		// TODO Auto-generated method stub
		return null;
	}


	public static CommandGroup FarRightToScale()
	{
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R')
		{
			output = RightToRightScale();
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = RightToLeftScale();
		}
		else
		{
			output = CrossLineCommand();
		}
		output.setName(getMethodName());
		return output;
	}
	
	public static CommandGroup RightStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.OUTSIDE_OPPOSITE_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new TiltCommand());
		output.addSequential(new GrabberCommand());
//		output.addParallel(command); does two things @ the same time, which is what we'll want for later.
		return output;
	}
	
	public static CommandGroup RightStationToRightSwitch() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.OUTSIDE_SAME_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	//TODO put in correct measurements
	public static CommandGroup CenterStationToRightSwitch() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		System.out.println("methodName:" + getMethodName());
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.CENTER_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	
	public static CommandGroup CenterStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		System.out.println("methodName:" + getMethodName());
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.CENTER_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY ,RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup LeftStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		System.out.println("LeftStationToLeftSwitch() Called");
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.OUTSIDE_SAME_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup LeftStationToRightSwitch() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		System.out.println("LeftStationToRightSwitch() Called");
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD1, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.OUTSIDE_OPPOSITE_AUTO_TRANSLATE_FWD, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(RobotMap.AUTO_FWD2, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup LeftToLeftScale() {
		CommandGroup output; 
		output = new CommandGroup(getMethodName());
		System.out.println("LeftToLeftScale() Called");
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(7, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup LeftToRightScale() {
		CommandGroup output; 
		output = new CommandGroup(getMethodName());
		System.out.println("LeftToRightScale() Called");
		
		output.addSequential(new AutonomousStraightCommand(207.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(208, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(235, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(96, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(7, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new ElevatorCommand(Height.SCALE)); //Going to use the elevator & deliver it to the highest value. Uses SCALE constant(Units: in')
		return output;
	}
	
	public static CommandGroup RightToRightScale() {
		CommandGroup output; 
		output = new CommandGroup(getMethodName());
		System.out.println("RightToRightScale() Called");
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(7, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup RightToLeftScale() {
		CommandGroup output; 
		output = new CommandGroup(getMethodName());
		System.out.println("RightToLeftScale() Called");
		output.addSequential(new AutonomousStraightCommand(207.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(208, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(235, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(96, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(7, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		
		return output;
	}
	
	public static CommandGroup ReturnFromRight() {
		CommandGroup output; 
		output = new CommandGroup(getMethodName());
		System.out.println("LeftToRightScale() Called");
		
		output.addSequential(new AutonomousStraightCommand(-7, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(-96, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-235, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-207.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));

		return output;
	}
	
	
	public static FMSInfo getFMSInfo()
	{
		FMSInfo fmsInfo = new FMSInfo();
		DriverStation driveStation= DriverStation.getInstance();
		
		System.out.println("FMS Attached: " + driveStation.isFMSAttached());
			
		//FHE: How do we test "IsFMSAttached())
		
			String gameSpecificMessage = "";

    		
    		//driveStation.waitForData();
        	fmsInfo.alliance = driveStation.getAlliance();
        	gameSpecificMessage = driveStation.getGameSpecificMessage();

    		fmsInfo.switchLocation = gameSpecificMessage.charAt(0);
        	fmsInfo.scaleLocation = gameSpecificMessage.charAt(1);
        	fmsInfo.opponentSwitchLocation = gameSpecificMessage.charAt(2);
        	fmsInfo.driveStation = driveStation.getLocation();
        	fmsInfo.isAutonomous = driveStation.isAutonomous();
        	fmsInfo.isInitalized = true;
		
		
		return fmsInfo;
	}

	
	public static CommandGroup DoNothingCommand() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		
		return output;
	}
	
	public static CommandGroup CrossLineCommand() {
		CommandGroup output;
		output = new CommandGroup(getMethodName());
		output.addSequential(new AutonomousStraightCommand(138.0, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		return output;
	}
	
	public static CommandGroup RotateTest() {
		System.out.println(getMethodName() + " Called");
		CommandGroup output = new CommandGroup(getMethodName());
		double targetAngle = 90;
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, targetAngle, RobotMap.AUTO_TURN_ACCELERATION)); // _, targetAngle, _)
		
		
		return output;
	}
	
	public static String getMethodName()
	{
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		return methodName;
	}
}