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
import org.usfirst.frc.team2635.robot.commands.GrabberOpen;
import org.usfirst.frc.team2635.robot.commands.TiltCommand;
import org.usfirst.frc.team2635.robot.commands.TiltDownCommand;
import org.usfirst.frc.team2635.robot.commands.TiltUpCommand;
import org.usfirst.frc.team2635.robot.subsystems.Elevator.Height;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

public class FarRightAutonomousSequences {

	public static CommandGroup FarRightToSwitch(){
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.switchLocation == 'R')
		{
			output = FarRightToRightSwitch();
			
		}
		else if (fmsInfo.switchLocation == 'L'){
			output = FarRightToLeftSwitch();
		}
		else
		{
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	private static CommandGroup FarRightToRightSwitch() {
		// TODO Auto-generated method stub, implement this thingie.
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(148, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousStraightCommand(20, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 1.5));
		output.addSequential(new TiltDownCommand(1));
		output.addSequential(new GrabberOpen(2));
		output.addSequential(new TiltUpCommand(2));
		output.addSequential(new ElevatorCommand(Height.GROUND));
		return output;
	}
	


	private static CommandGroup FarRightToLeftSwitch() {
		//TODO find true values 
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(205, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(180, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(10, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 1.0));
		output.addSequential(new TiltDownCommand(1));
		output.addSequential(new GrabberOpen(2));
		output.addSequential(new TiltUpCommand(2));
		output.addSequential(new ElevatorCommand(Height.GROUND));

		
		return output;
	}
	
	
	public static CommandGroup FarRightToScale()
	{
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R')
		{
			output = RightToRightScale();
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = RightToLeftScale();
		}
		else
		{
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	
	public static CommandGroup RightToRightScale() {
		CommandGroup output; 
		output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.CLIMB));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(30, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 1.5));
		output.addSequential(new TiltDownCommand(1));
		output.addSequential(new GrabberOpen(2));
		output.addSequential(new AutonomousStraightCommand(-30, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new TiltUpCommand(2));
		output.addSequential(new ElevatorCommand(Height.GROUND));
		
		return output;
	}
	

	
	
	public static CommandGroup RightToLeftScale() {
		CommandGroup output; 
		output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(208, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(240, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.CLIMB));
		output.addSequential(new AutonomousStraightCommand(96, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(30, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 1.5));
		output.addSequential(new TiltDownCommand(1));
		output.addSequential(new GrabberOpen(2));
		output.addSequential(new AutonomousStraightCommand(-30, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new TiltUpCommand(1));
		output.addSequential(new ElevatorCommand(Height.GROUND));

		
		return output;
	}
	
//	public static CommandGroup ReturnFromRight() {
//		CommandGroup output; 
//		output = new CommandGroup();
//		
//		output.addSequential(new AutonomousStraightCommand(-20, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
//		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
//		output.addSequential(new AutonomousStraightCommand(-96, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
//		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
//		output.addSequential(new AutonomousStraightCommand(-235, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
//		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
//		output.addSequential(new AutonomousStraightCommand(-207.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
//
//		return output;
//	}
}
