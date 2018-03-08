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
		output.addSequential(new AutonomousStraightCommand(148, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousStraightCommand(25, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 2.5));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		return output;
	}
	
	
	


	private static CommandGroup FarRightToLeftSwitch() {
		//TODO find true values 
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(200, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 5.0));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(195, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION),3.0);
		output.addSequential(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 45, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-30, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION, 2.0));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 45, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(50, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 2.0));

		MotionMagicLibrary.DeliverCubeAndBackup(output);

		
		return output;
	}
	
	
	
	public static CommandGroup FarRightToScale() {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarRightToRightScale();
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = FarRightToLeftScale();
		} else {
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	public static CommandGroup FarRightToScaleAndWait() {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarRightToRightScaleAndWait();
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = FarRightToLeftScaleAndWait();
		} else {
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	public static CommandGroup FarRightToRightScale() {
		CommandGroup output; 
		output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SCALE));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(10, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 1.5));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		
		return output;
	}
	

	
	
	public static CommandGroup FarRightToLeftScale() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(196, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		//Make sure that SCALE is high enough on comp bot
		output.addParallel(new ElevatorCommand(Height.SCALE));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(62, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION));
		MotionMagicLibrary.DeliverCubeAndBackup(output);


		
		return output;
	}
	
	public static CommandGroup FarRightToLeftScaleAndWait() {
		CommandGroup output; 
		output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(208, RobotMap.AUTO_DRIVE_VELOCITY));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(245, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(84, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		//output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		
		return output;
	}
	
	public static CommandGroup FarRightToRightScaleAndWait() {
		CommandGroup output; 
		output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		
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
