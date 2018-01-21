/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2635.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2635.robot.commands.DriveCommand;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.subsystems.Drive;
import org.usfirst.frc.team2635.robot.subsystems.FMS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static Drive drive;
	public static FMS fms;
	
	Joystick leftStick;
	Joystick rightStick;
	
	DriveCommand driveCommand;
	AutonomousCommand autoCommand;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		drive = new Drive();
		fms = new FMS();
		
		leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		
		driveCommand = new DriveCommand(leftStick, rightStick);
		autoCommand = new AutonomousCommand();
		
		//m_chooser.addObject("My Auto", autoCommand);
		SmartDashboard.putData("Auto mode", m_chooser);
		
		SmartDashboard.putNumber("P:", RobotMap.MOTION_MAGIC_P);
		SmartDashboard.putNumber("I:", RobotMap.MOTION_MAGIC_I);
		SmartDashboard.putNumber("D:", RobotMap.MOTION_MAGIC_D);
		SmartDashboard.putNumber("F:", RobotMap.MOTION_MAGIC_F);
		
		SmartDashboard.putNumber("Distance:", 0);
	
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

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
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
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command
		
		if (autoCommand != null) {
			drive.autoInit();
			System.out.println("Trying to Start AutoCommand");
			autoCommand.start();
		}
		
		MotionMagicLibrary.CenterStationToRightSwitch().start();
		
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
		
		RobotMap.INCHES = SmartDashboard.getNumber("Distance:", RobotMap.MOTION_MAGIC_DISTANCE);
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
		if (driveCommand != null) {
			driveCommand.start();
		}
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
}
