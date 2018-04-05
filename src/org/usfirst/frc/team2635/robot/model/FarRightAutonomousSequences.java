package org.usfirst.frc.team2635.robot.model;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.RobotMap;
import org.usfirst.frc.team2635.robot.commands.AutonomousNavxRotate;
import org.usfirst.frc.team2635.robot.commands.AutonomousStraightCommand;
import org.usfirst.frc.team2635.robot.commands.AutonomousTurnCommand;
import org.usfirst.frc.team2635.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2635.robot.commands.GrabberClosed;
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

public class FarRightAutonomousSequences {

	public static CommandGroup FarRightToScale(boolean deliverCube) {
	
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		if (fmsInfo.scaleLocation == 'R') {
			output = FarRightToRightScale(deliverCube);
		}
		else if (fmsInfo.scaleLocation == 'L'){
			output = FarRightToLeftScale(deliverCube);
			//output = FarRightToLeftScaleOld(false); //To wait, uncomment out this line, and comment out the line above
		} else {
			output = MotionMagicLibrary.CrossLineCommandBackward();
		}
		output.setName(MotionMagicLibrary.getMethodName());
		return output;
	}

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
	
	public static CommandGroup FarRightToBestTarget() {
		CommandGroup output = new CommandGroup();
		FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
		
		//If Scale is on Right, go to scale.
		//If Switch is on right, go to switch.
		//if Far scale and wait.
		
		if(fmsInfo.scaleLocation == 'R'){
			output = FarRightToRightScale(true);
		}
		else if(fmsInfo.switchLocation == 'R'){
			output = FarRightToRightSwitch();
		}
		else if(fmsInfo.scaleLocation == 'L'){
			output = FarRightToLeftScale(false);
		}
		else{
			output = MotionMagicLibrary.CrossLineCommand();
		}
		
		return output;
		
	}
	
	private static CommandGroup FarRightToRightSwitch() {
		// TODO Auto-generated method stub, implement this thingie.
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(-148, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		output.addSequential(new PauseCommand(0.25));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousStraightCommand(25, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 2.5));
		MotionMagicLibrary.DeliverCubeAndBackup(output);
		return output;
	}
	
	
	


	private static CommandGroup FarRightToLeftSwitch() {
		CommandGroup output = new CommandGroup();
		output.addSequential(new AutonomousStraightCommand(-210, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION, 4.0));
		output.addSequential(new PauseCommand(0.5));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-225, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION,3.0));
		output.addSequential(new PauseCommand(0.5));
		output.addParallel(new ElevatorCommand(Height.SWITCH));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -50, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(42, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION, 2.0));
		MotionMagicLibrary.DeliverCubeAndBackup(output);

		
		return output;
	}
	
	
	

	


	public static CommandGroup FarRightToRightScale(boolean deliverCube) {
		CommandGroup output = new CommandGroup(); 
		output.addSequential(new AutonomousStraightCommand(-247.25, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.5));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, -160, RobotMap.AUTO_TURN_ACCELERATION));//Should be 4.9" away
		output.addSequential(new AutonomousStraightCommand(37.75, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION, 1.5));
		if (deliverCube) {
			MotionMagicLibrary.DeliverCubeAndBackup(output);
		}
		//GetAnotherCube(output);
		return output;
	}
	
	public static void GetAnotherCube(CommandGroup cmdGroup) {
		
		cmdGroup.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 140, RobotMap.AUTO_TURN_ACCELERATION));
		cmdGroup.addParallel(new AutonomousStraightCommand(50, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));
		cmdGroup.addSequential(new TiltDownCommand(0.75));
		cmdGroup.addSequential(new GrabberClosed(1.00));
		//cmdGroup.addSequential(new TiltUpCommand(0.75));
		cmdGroup.addParallel(new AutonomousStraightCommand(-20, RobotMap.SHORT_DRIVE_AUTONOMOUS_VELOCITY, RobotMap.SHORT_DRIVE_AUTONOMOUS_ACCELERATION));


	}
	
	public static CommandGroup FarRightToLeftScaleOld(boolean deliverCube) {
		CommandGroup output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(-217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-210, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.5));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.25));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(40, RobotMap.APPROACH_SCALE_VELOCITY, RobotMap.APPROACH_SCALE_ACCELERATION));
		if (deliverCube) {
			MotionMagicLibrary.DeliverCubeAndBackup(output);
		}
		return output;
	}
	
	
	public static CommandGroup FarRightToLeftScale(boolean deliverCube) {
		CommandGroup output = new CommandGroup(MotionMagicLibrary.getMethodName());
		output.addSequential(new AutonomousStraightCommand(-217.125, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.4));
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
		output.addSequential(new AutonomousStraightCommand(-215, RobotMap.AUTO_DRIVE_VELOCITY, RobotMap.AUTO_DRIVE_ACCELERATION));
		output.addSequential(new PauseCommand(0.4));
		if (deliverCube) {
			output.addParallel(new ElevatorCommand(Height.CLIMB));
			output.addSequential(new PauseCommand(0.25));
		}
		output.addSequential(new AutonomousTurnCommand(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION));
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
	



}
