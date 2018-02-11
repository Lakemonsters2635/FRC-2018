/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.reflect.Method;

import org.usfirst.frc.team2635.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2635.robot.commands.AutonomousNavxRotate;
import org.usfirst.frc.team2635.robot.commands.ClimbDownCommand;
import org.usfirst.frc.team2635.robot.commands.ClimbUpCommand;
import org.usfirst.frc.team2635.robot.commands.DriveCommand;
import org.usfirst.frc.team2635.robot.commands.GearShiftCommand;
import org.usfirst.frc.team2635.robot.commands.GetFMSCommand;
import org.usfirst.frc.team2635.robot.commands.GrabberCommand;
import org.usfirst.frc.team2635.robot.commands.TiltCommand;
import org.usfirst.frc.team2635.robot.commands.ToggleDriveModeCommand;
import org.usfirst.frc.team2635.robot.commands.VisionLightCommand;
import org.usfirst.frc.team2635.robot.model.FMSInfo;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.subsystems.Bling;
import org.usfirst.frc.team2635.robot.subsystems.Climber;
import org.usfirst.frc.team2635.robot.subsystems.Drive;
import org.usfirst.frc.team2635.robot.subsystems.Elevator;
import org.usfirst.frc.team2635.robot.subsystems.FMS;
import org.usfirst.frc.team2635.robot.subsystems.Gearbox;
import org.usfirst.frc.team2635.robot.subsystems.Grabber;
import org.usfirst.frc.team2635.robot.subsystems.Tilt;
import org.usfirst.frc.team2635.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;
	public static Drive drive;
	public static FMS fms;
	public static Vision vision;
	public static Climber climber;
	public static Elevator elevator;
	public static Gearbox gearbox;
	public static Grabber grabber;
	public static Tilt tilter;
	public static Bling bling;
	
	DriveCommand driveCommand;
	AutonomousCommand autoCommand;
	VisionLightCommand visionCommand;
	ToggleDriveModeCommand toggleDriveModeCommand;
	ClimbUpCommand climbUpCommand;
	ClimbDownCommand climbDownCommand;
	GearShiftCommand gearShiftCommand;
	GrabberCommand grabberCommand;
	TiltCommand tiltCommand;
	CommandGroup returnCommand;
	AutonomousNavxRotate navxRotateCommand;
	
	Command doNothingCmd;
	
	Command rightStation;
	Command centerStation;
	Command leftStation;
	
	Command m_autonomousCommand;
	SendableChooser m_chooser; 
	//SendableChooser chooser = new SendableChooser();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drive = new Drive();
		fms = new FMS();
		vision = new Vision();
		climber = new Climber();
		elevator = new Elevator();
		gearbox = new Gearbox();
		grabber = new Grabber();
		bling = new Bling();
		
		m_chooser = new SendableChooser();
		
		
		driveCommand = new DriveCommand();
		autoCommand = new AutonomousCommand();
		visionCommand = new VisionLightCommand();
		toggleDriveModeCommand = new ToggleDriveModeCommand();
		climbUpCommand = new ClimbUpCommand();
		climbDownCommand = new ClimbDownCommand();
		gearShiftCommand = new GearShiftCommand();
		returnCommand = MotionMagicLibrary.ReturnFromRight();
		navxRotateCommand = new AutonomousNavxRotate(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION);
		//grabberCommand = new GrabberCommand();
		//tiltCommand = new TiltCommand();
		
		vision.ledOff();
		
		InitializeChooser();
		
		//m_chooser.addObject("My Auto", autoCommand);
		//SmartDashboard.putData("Auto mode", m_chooser);
		
		SmartDashboard.putNumber("P:", RobotMap.MOTION_MAGIC_P);
		SmartDashboard.putNumber("I:", RobotMap.MOTION_MAGIC_I);
		SmartDashboard.putNumber("D:", RobotMap.MOTION_MAGIC_D);
		SmartDashboard.putNumber("F:", RobotMap.MOTION_MAGIC_F);
		
		oi.visionButton.toggleWhenPressed(visionCommand);
		oi.driveModeButton.toggleWhenPressed(toggleDriveModeCommand);
		oi.climbUpButton.whileHeld(climbUpCommand);
		oi.climbDownButton.whileHeld(climbDownCommand);
		oi.gearShiftButton.toggleWhenPressed(gearShiftCommand);
		oi.grabberButtonLeft.toggleWhenPressed(grabberCommand);
		oi.tiltToggleButton.toggleWhenPressed(tiltCommand);
		oi.returnButton.whenPressed(returnCommand);
		//oi.navxRotateButton.whenPressed(navxRotateCommand);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		System.out.println("disabledInit");
		//Commands are automatically canceled when robot is disabled,
		//However you can set a command to run while the robot is disabled using
		//driveCommand.setRunWhenDisabled(true);
		if (autoCommand != null && autoCommand.isRunning())
		{
			autoCommand.cancel();
		}
		if (driveCommand != null && driveCommand.isRunning())
		{
			driveCommand.cancel();
		}
		vision.ledOff();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		vision.ledOff();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		RobotMap.MOTION_MAGIC_P = SmartDashboard.getNumber("P:", RobotMap.MOTION_MAGIC_P);
		RobotMap.MOTION_MAGIC_I = SmartDashboard.getNumber("I:", RobotMap.MOTION_MAGIC_I);   
		RobotMap.MOTION_MAGIC_D = SmartDashboard.getNumber("D:", RobotMap.MOTION_MAGIC_D);
		RobotMap.MOTION_MAGIC_F = SmartDashboard.getNumber("F:", RobotMap.MOTION_MAGIC_F);
		
		drive.autoInit();
		
		String selectedCommandName = (String) m_chooser.getSelected();
		if (selectedCommandName == "RightStation")
		{
			m_autonomousCommand = MotionMagicLibrary.RightStation();
		}
		else if (selectedCommandName == "CenterStation")
		{
			m_autonomousCommand = MotionMagicLibrary.CenterStation();
		}
		else if (selectedCommandName == "LeftStation")
		{
			m_autonomousCommand = MotionMagicLibrary.LeftStation();
		}
		else if (selectedCommandName == "FarLeftToScale")
		{
			m_autonomousCommand = MotionMagicLibrary.FarLeftToScale();
		}
		else if (selectedCommandName == "FarRightToScale")
		{
			m_autonomousCommand = MotionMagicLibrary.FarRightToScale();
		}
		else if (selectedCommandName == "RotateTest")
		{
			m_autonomousCommand = MotionMagicLibrary.RotateTest();
		}

//		Class<?> c = Class.forName("MotionMagicLibrary");
//		Method method = c.getDeclaredMethod(selectedCommandName, null);
//		m_autonomousCommand = (Command) method.invoke(c, null);
		//m_autonomousCommand.getName();
		m_autonomousCommand.start();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command
		
		
//		if (autoCommand != null) {
//			drive.autoInit();
//			System.out.println("Trying to Start AutoCommand");
//			autoCommand.start();
//		}
//		FMSInfo fmsInfo = new FMSInfo();
//		GetFMSCommand fmsInfoCmd = new GetFMSCommand(fmsInfo);
//		fmsInfoCmd.start();
		//MotionMagicLibrary.CenterStationToLeftSwitch().start();
		vision.ledOn();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.MOTION_MAGIC_P = SmartDashboard.getNumber("P:", RobotMap.MOTION_MAGIC_P);
		RobotMap.MOTION_MAGIC_I = SmartDashboard.getNumber("I:", RobotMap.MOTION_MAGIC_I);   
		RobotMap.MOTION_MAGIC_D = SmartDashboard.getNumber("D:", RobotMap.MOTION_MAGIC_D);
		RobotMap.MOTION_MAGIC_F = SmartDashboard.getNumber("F:", RobotMap.MOTION_MAGIC_F);
	}

	@Override
	public void teleopInit() {
		
		if (autoCommand != null) {
			autoCommand.cancel();
		}
		
		if (drive != null)drive.teleInit();
		
		if (driveCommand != null) {
			driveCommand.start();
			vision.driveMode();
		}
		vision.ledOff();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	public void InitializeChooser()
	{
		
		
		//doNothingCmd = MotionProfileLibrary.doNothing();

		//SendableBuilder builder = null;
		//m_chooser.initSendable(builder);;
		m_chooser.addDefault("Do Nothing", "DoNothingCommand");
		
		m_chooser.addObject("Left Station to Switch", "LeftStation");
		m_chooser.addObject("Center Station to Switch", "CenterStation");
		m_chooser.addObject("Right Station to Switch", "RightStation");
		
		m_chooser.addObject("Far Left to Scale", "FarLeftToScale");
		m_chooser.addObject("Far Right to Scale", "FarRightToScale");
		m_chooser.addObject("Rotate Test", "RotateTest");
		
		//chooser.addObject("Center", centerStationToLeftSwitch);
		//chooser.addObject("Right", centerStationToLeftSwitch);
		//chooser.addObject("Left", centerStationToLeftSwitch);
//		
//		Command centerStationToRightSwitch;
//		Command rightStationToRightSwitch;
//		Command rightStationToRightSwitch;
//		Command leftStationToLeftSwitch;
//		Command leftStationToRightSwitch;
		
//		m_chooser.addObject("Left Gear", center);
//		m_chooser.addObject("Left Gear Simple", leftGearSimple);
//		m_chooser.addObject("Right Gear", rightGear);
//		m_chooser.addObject("Vision Test", visionTest);
//		m_chooser.addObject("Rotation Test", rotateTest);

		
		SmartDashboard.putData("Auto Selector", m_chooser);
	}
}
