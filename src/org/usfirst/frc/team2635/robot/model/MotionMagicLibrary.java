package org.usfirst.frc.team2635.robot.model;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.AutonomousStraightCommand;
import org.usfirst.frc.team2635.robot.commands.AutonomousTurnCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

public class MotionMagicLibrary
{


	public static MotionParameters getArcRotationParameters(double targetAngle, 
														   double wheelRadiusInches,
														   double turnRadiusInches, 
														   double wheelSeparationInches,  
														   double velocity, 
														   boolean Clockwise, 
														   boolean rotateCenter)
	{
		double inchesPerRotation = wheelRadiusInches * 2 * Math.PI;
		
		double arcLengthRight;
		double archLengthLeft;
		double rightWheelRotations;
		double leftWheelRotations;
		
		if (rotateCenter)
		{			
			//To rotate around center.
			double radius = wheelSeparationInches/2.0;
			//radius is 1/2 of wheelSeparationInches
			//ArcLengh = radius * angle in radians
			
			arcLengthRight = radius *  (2*Math.PI)/360.0 * targetAngle;
			archLengthLeft = arcLengthRight;
			rightWheelRotations = arcLengthRight/inchesPerRotation;
			leftWheelRotations = archLengthLeft/inchesPerRotation;

		}
		else
		{	
			arcLengthRight = turnRadiusInches *  (2*Math.PI)/360.0 * targetAngle;
			archLengthLeft = (turnRadiusInches + wheelSeparationInches)  *  (2*Math.PI)/360.0 * targetAngle;
			rightWheelRotations = arcLengthRight/inchesPerRotation;
			leftWheelRotations = -archLengthLeft/inchesPerRotation;
		}
		
		
		double velocityRatio = Math.abs(leftWheelRotations/rightWheelRotations);
		
		double rightVelocity = velocity;
		double leftVelocity = velocity * velocityRatio;
		
		double rightAcceleration =  rightVelocity;
		double leftAcceleration =  leftVelocity;
		
		
		if (!Clockwise && !rotateCenter)
		{
			double tmpRotation = rightWheelRotations;
			rightWheelRotations = leftWheelRotations;
			leftWheelRotations = tmpRotation;
			
			double tmpAcceleration = rightAcceleration;
			rightAcceleration = leftAcceleration;
			leftAcceleration = tmpAcceleration;
			
			double tmpVelocity = rightVelocity;
			rightVelocity = leftVelocity;
			leftVelocity = tmpVelocity;
		}
		else if (Clockwise && rotateCenter)
		{
			rightWheelRotations = -rightWheelRotations;
			leftWheelRotations = -leftWheelRotations;
		}
		
		
		MotionParameters rotationParams = new MotionParameters();
		rotationParams.rightAcceleration = (int) rightAcceleration;
		rotationParams.leftAcceleration = (int) leftAcceleration;
		rotationParams.rightVelocity     = (int) rightVelocity;
		rotationParams.leftVelocity     = (int) leftVelocity;
		rotationParams.rightWheelRotations = rightWheelRotations * 1000;
		rotationParams.leftWheelRotations = leftWheelRotations * 1000;

		return rotationParams;

	}
	
	
	public static MotionParameters getRotationParameters(double targetAngle,    double wheelRadiusInches, double wheelSeparationInches,  double velocity)	
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
			
			double rightAcceleration =  rightVelocity;
			double leftAcceleration =  leftVelocity;
		
			
			
			MotionParameters rotationParams = new MotionParameters();
			rotationParams.rightAcceleration = (int) rightAcceleration;
			rotationParams.leftAcceleration = (int) leftAcceleration;
			rotationParams.rightVelocity     = (int) rightVelocity;
			rotationParams.leftVelocity     = (int) leftVelocity;
			rotationParams.rightWheelRotations = rightWheelRotations*1000;
			rotationParams.leftWheelRotations = leftWheelRotations*1000;
			
			return rotationParams;
			}

	
	public static MotionParameters getDriveParameters(double wheelRadiusInches, double distanceInches, double velocity, boolean reverse)
	{
		

		double inchesPerRotation = wheelRadiusInches * 2 * Math.PI;
		
		//double arcLengthInner;
		//double archLengthOuter;
		double velocit = velocity;
		double acceleration = velocity;
		
		//FOR COMPETITION BOT DO THE FOLLOWING
		double leftWheelRotations = -distanceInches/inchesPerRotation;
		//END COMPETITION BOT
		
		double rightWheelRotations = distanceInches/inchesPerRotation;
		
		if (reverse)
		{			
			leftWheelRotations = -leftWheelRotations;
			rightWheelRotations = -rightWheelRotations;
		}
		
		MotionParameters driveParams = new MotionParameters();
		driveParams.leftAcceleration = (int) acceleration;
		driveParams.rightAcceleration = (int) acceleration;
		driveParams.leftWheelRotations = leftWheelRotations * 1000;
		driveParams.rightWheelRotations = rightWheelRotations * 1000;
		driveParams.leftVelocity     = (int) velocit;
		driveParams.rightVelocity     = (int) velocit;
		return driveParams;

	}
	
	public static CommandGroup RightStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(150, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
	
	public static CommandGroup RightStationToRightSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(41.6, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
	
	public static CommandGroup CenterStationToRightSwitch() {
		CommandGroup output; 
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(40, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
	
	public static CommandGroup CenterStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(40, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
	
	public static CommandGroup LeftStationToLeftSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(41.6, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
	
	public static CommandGroup LeftStationToRightSwitch() {
		CommandGroup output;
		output = new CommandGroup();
		
		output.addSequential(new AutonomousStraightCommand(60, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90));
		output.addSequential(new AutonomousStraightCommand(150, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90));
		output.addSequential(new AutonomousStraightCommand(51, RobotMap.AUTO_DRIVE_VELOCITY));
		
		return output;
	}
}