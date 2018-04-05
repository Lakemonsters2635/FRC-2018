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
import org.usfirst.frc.team2635.robot.commands.PauseCommand;
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

	
	public  static CommandGroup FarLeftToScale(boolean deliverCube) {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarLeftToRightScale(deliverCube);
			//output = FarLeftToRightScaleOld(false); //To wait, uncomment out this line, and comment out the line above
		} else if (fmsInfo.scaleLocation == 'L') {
			output = FarLeftToLeftScale(deliverCube);
		} else {
			output = MotionMagicLibrary.CrossLineCommandBackward();
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
	

	
	private static CommandGroup FarLeftToLeftSwitch() {
		// TODO Auto-generated method stub, implement this thingie.
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(-148, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		output.addSequential(new PauseCommand(0.25));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousStraightCommand(25, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 2.5));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		
		return output;
	}
	


	
	private static CommandGroup FarLeftToRightSwitch() {
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(-210, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 4.0));
		output.addSequential(new PauseCommand(0.5));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-225, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 3.0));
		output.addSequential(new PauseCommand(0.5));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 50, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(42, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION, 2.0));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		return output;
	}
	
	

		
	public static CommandGroup FarLeftToLeftScale(boolean deliverCube) {
		CommandGroup output = new CommandGroup(); 
		output.addSequential(new AutonomousStraightCommand(-249.25, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.5));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 160, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(37.75, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 1.5));
		if (deliverCube) {
			MotionMagicLibrary.DeliverCubeAndBackup(output);
		}
		return output;
	}
	
	public static CommandGroup FarLeftToRightScaleOld(boolean deliverCube) {
		CommandGroup output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(-217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-215, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.25));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(40, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION));
		if (deliverCube) {
			MotionMagicLibrary.DeliverCubeAndBackup(output);
		}

		return output;
	}
	
	public static CommandGroup FarLeftToRightScale(boolean deliverCube) {
		CommandGroup output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(-217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.4));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-210, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.4));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.25));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(40, 400, 500, 1.75));
		if (deliverCube) {
			output.addSequential(new TiltDownCommand(0.5));
			output.addParallel(new GrabberOpen(0.5));
			output.addSequential(new TiltUpCommand(0.5));
			output.addParallel(new AutonomousStraightCommand(-20, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));		
			output.addSequential(new ElevatorCommand(Height.GROUND));

		}
		return output;
	}
	
	
	public static CommandGroup FarLeftToBestTarget() {
		System.out.println("FarLeftToBestTarget");
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		//If Scale is on Right, go to scale.
		//If Switch is on right, go to switch.
		//if Far scale and wait.
		
		if(fmsInfo.scaleLocation == 'L'){
			output = FarLeftToLeftScale(true);
			System.out.println("FarLeftToLeftScale");
		}
		else if(fmsInfo.switchLocation == 'L'){
			output = FarLeftToLeftSwitch();
			System.out.println("FarLeftToLeftSwitch");
		}
		else if(fmsInfo.scaleLocation == 'R'){
			output = FarLeftToRightScale(false);
			System.out.println("FarLeftToLeftSwitch");
		}
		else{
			output = MotionMagicLibrary.CrossLineCommand();
			System.out.println("CrossLineCommand");
		}
		
		return output;
		
	}
	
	


	
}
