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
public class FarLeftAutonomousSequences {

	
	public  static CommandGroup FarLeftToScale() {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarLeftToRightScale();
		} else if (fmsInfo.scaleLocation == 'L') {
			output = FarLeftToLeftScale();
		} else {
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	public static CommandGroup FarLeftToSwitch(){
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.switchLocation == 'R')
		{
			output = FarLeftToRightSwitch();
			
		}
		else if (fmsInfo.switchLocation == 'L'){
			output = FarLeftToLeftSwitch();
		}
		else
		{
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	public  static CommandGroup FarLeftToScaleAndWait() {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarLeftToRightScaleAndWait();
		} else if (fmsInfo.scaleLocation == 'L') {
			output = FarLeftToLeftScaleAndWait();
		} else {
			output = MotionMagicLibrary.CrossLineCommand();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}
	
	private static CommandGroup FarLeftToLeftSwitch() {
		// TODO Auto-generated method stub, implement this thingie.
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(148, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousStraightCommand(25, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 2.5));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		
		return output;
	}
	
	

	
	private static CommandGroup FarLeftToRightSwitch() {
		//TODO find true values 
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(200, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(195, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -45, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-30, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION,1.0));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -45, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(50, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION,1.0));

		MotionMagicLibrary.DeliverCubeAndBackup(output);


		
		return output;
	}
	
	public static CommandGroup FarLeftToLeftScale() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		System.out.println("LeftToLeftScale() Called");
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SCALE));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		MotionMagicLibrary.DeliverCubeAndBackup(output);

		
		return output;
	}
	

	
	public static CommandGroup FarLeftToRightScale() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(196, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SCALE));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));

		output.addSequential(new AutonomousStraightCommand(62, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION));
		
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		
		return output;
	}
	
	public static CommandGroup FarLeftToRightScaleAndWait() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		System.out.println("LeftToRightScale() Called");
		
		output.addSequential(new AutonomousStraightCommand(217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(245, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		//output.addSequential(new AutonomousStraightCommand(84, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		//output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away

		return output;
	}
	
	
	
	
	public static CommandGroup FarLeftToRightScaleBackward() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(-217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-240, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SCALE));
		output.addSequential(new AutonomousStraightCommand(-96, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(25, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 1.5));
		
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		
		return output;
	}
	
	public static CommandGroup FarLeftToLeftScaleAndWait() {
		CommandGroup output; 
		output = new CommandGroup(MotionMagicLibrary.getMethodName());
		System.out.println("LeftToLeftScale() Called");
		output.addSequential(new AutonomousStraightCommand(290, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		
		return output;
	}
	
}
