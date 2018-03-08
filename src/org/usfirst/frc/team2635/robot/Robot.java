/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
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
import org.usfirst.frc.team2635.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2635.robot.commands.ElevatorControl;
import org.usfirst.frc.team2635.robot.commands.ElevatorDownCommand;
import org.usfirst.frc.team2635.robot.commands.ElevatorUpCommand;
import org.usfirst.frc.team2635.robot.commands.ExpressElevatorDownCommand;
import org.usfirst.frc.team2635.robot.commands.ExpressElevatorUpCommand;
import org.usfirst.frc.team2635.robot.commands.GearShiftCommand;
import org.usfirst.frc.team2635.robot.commands.GrabberClosed;
import org.usfirst.frc.team2635.robot.commands.GrabberCommand;
import org.usfirst.frc.team2635.robot.commands.GrabberOpen;
import org.usfirst.frc.team2635.robot.commands.TiltCommand;
import org.usfirst.frc.team2635.robot.commands.ToggleDriveModeCommand;
import org.usfirst.frc.team2635.robot.commands.VisionLightCommand;
import org.usfirst.frc.team2635.robot.model.FMSInfo;
import org.usfirst.frc.team2635.robot.model.FarLeftAutonomousSequences;
import org.usfirst.frc.team2635.robot.model.FarRightAutonomousSequences;
import org.usfirst.frc.team2635.robot.model.MotionMagicLibrary;
import org.usfirst.frc.team2635.robot.subsystems.Bling;
import org.usfirst.frc.team2635.robot.subsystems.Climber;
import org.usfirst.frc.team2635.robot.subsystems.Drive;
import org.usfirst.frc.team2635.robot.subsystems.Elevator;
import org.usfirst.frc.team2635.robot.subsystems.FMS;
import org.usfirst.frc.team2635.robot.subsystems.Gearbox;
import org.usfirst.frc.team2635.robot.subsystems.Grabber;
import org.usfirst.frc.team2635.robot.subsystems.LimitSwitch;
import org.usfirst.frc.team2635.robot.subsystems.Tilt;
import org.usfirst.frc.team2635.robot.subsystems.Vision;
import org.usfirst.frc.team2635.robot.subsystems.Elevator.Height;

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
	public static LimitSwitch limitSwitch;
	
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
	ElevatorControl elevatorControl;
	
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
		tilter = new Tilt();
		gearbox = new Gearbox();
		grabber = new Grabber();
		bling = new Bling();
		limitSwitch = new LimitSwitch();
		
		m_chooser = new SendableChooser();
		
		
		driveCommand = new DriveCommand();
		autoCommand = new AutonomousCommand();
		visionCommand = new VisionLightCommand();
		toggleDriveModeCommand = new ToggleDriveModeCommand();
		climbUpCommand = new ClimbUpCommand();
		climbDownCommand = new ClimbDownCommand();
		gearShiftCommand = new GearShiftCommand();
		//returnCommand = FarRightAutonomousSequences.ReturnFromRight();
		navxRotateCommand = new AutonomousNavxRotate(RobotMap.AUTO_TURN_VELOCITY, 90, RobotMap.AUTO_TURN_ACCELERATION);
		elevatorControl = new ElevatorControl();
		grabberCommand = new GrabberCommand();
		tiltCommand = new TiltCommand();
		
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
		//oi.grabberButtonLeft.toggleWhenPressed(grabberCommand);
		oi.grabberButtonRight.toggleWhenPressed(grabberCommand);
	
		oi.tiltButton.toggleWhenPressed(tiltCommand);
		//oi.returnButton.whenPressed(returnCommand);
		oi.elevatorUpButton.whenPressed(new ElevatorUpCommand());
		oi.elevatorDownButton.whenPressed(new ElevatorDownCommand());
		oi.expressElevatorUpButton.whenPressed(new ExpressElevatorUpCommand());
		oi.expressElevatorDownButton.whenPressed(new ExpressElevatorDownCommand());
		//oi.elevatorTestButton.whenPressed(new ElevatorCommand(Height.SWITCH));
		//oi.closeGrabberTestButton.whenPressed(new GrabberClosed(1));
		//oi.openGrabberTestButton.whenPressed(new GrabberOpen(1));
		
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
		elevatorControl.cancel();
		vision.ledOff();
		
	}

	@Override
	public void disabledPeriodic() {
		
//			try {
//				bling.set(0);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
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
		
		switch(selectedCommandName) { 
	    case "RightStationToSwitch": 
	    	m_autonomousCommand = MotionMagicLibrary.RightStationToSwitch(); 
	        break; 
	    case "CenterStationToSwitch": 
	    	m_autonomousCommand = MotionMagicLibrary.CenterStationToSwitch();
	        break;
	    case "LeftStationToSwitch":
	    	m_autonomousCommand = MotionMagicLibrary.LeftStationToSwitch();
	    	break;
	    case "FarLeftToScale":
	    	m_autonomousCommand = FarLeftAutonomousSequences.FarLeftToScale();
	    	break;
	    case "FarRightToScale":
	    	m_autonomousCommand = FarRightAutonomousSequences.FarRightToScale();
	    	break;
	    case "FarLeftToScaleAndWait":
	    	m_autonomousCommand = FarLeftAutonomousSequences.FarLeftToScaleAndWait();
	    	break;
	    case "FarRightToScaleAndWait":
	    	m_autonomousCommand = FarRightAutonomousSequences.FarRightToScaleAndWait();
	    	break;
	    case "FarLeftToSwitch":
	    	m_autonomousCommand = FarLeftAutonomousSequences.FarLeftToSwitch();
	    	break;
	    case "FarRightToSwitch":
	    	m_autonomousCommand = FarRightAutonomousSequences.FarRightToSwitch();
	    	break;
	    case "RotateTest":
	    	m_autonomousCommand = MotionMagicLibrary.RotateTest();
	    	break;
	    case "RotateTestCCW":
	    	m_autonomousCommand= MotionMagicLibrary.RotateTestCCW();
	    	break;
	    case "DriveStraightTest":
	    	m_autonomousCommand = MotionMagicLibrary.DriveStraightTest();
	    	break;
	    default:
	    	m_autonomousCommand = MotionMagicLibrary.DoNothingCommand();
		}
		
//		Class<?> c = Class.forName("MotionMagicLibrary");
//		Method method = c.getDeclaredMethod(selectedCommandName, null);
//		m_autonomousCommand = (Command) method.invoke(c, null);
		//m_autonomousCommand.getName();
		elevatorControl.start();
		m_autonomousCommand.start();
		
	
		 

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
//		try {
//			FMSInfo fmsInfo = MotionMagicLibrary.getFMSInfo();
//		
//			if (fmsInfo.alliance == Alliance.Red) {
//				bling.set(1);
//			}
//			else if (fmsInfo.alliance == Alliance.Blue){
//				bling.set(3);
//			}
//			else if (fmsInfo.alliance == Alliance.Invalid) {
//				bling.set(2);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Scheduler.getInstance().run();
		RobotMap.MOTION_MAGIC_P = SmartDashboard.getNumber("P:", RobotMap.MOTION_MAGIC_P);
		RobotMap.MOTION_MAGIC_I = SmartDashboard.getNumber("I:", RobotMap.MOTION_MAGIC_I);   
		RobotMap.MOTION_MAGIC_D = SmartDashboard.getNumber("D:", RobotMap.MOTION_MAGIC_D);
		RobotMap.MOTION_MAGIC_F = SmartDashboard.getNumber("F:", RobotMap.MOTION_MAGIC_F);
		
		SmartDashboard.putNumber("Front Left Position", (double) drive.getFrontLeftPos());
		SmartDashboard.putNumber("Front Right Position", (double) drive.getFrontRightPos());
		SmartDashboard.putNumber("LeftError", drive.leftErrorReport);
		SmartDashboard.putNumber("RightError" , drive.rightErrorReport);
		SmartDashboard.putNumber("Error Delta" , drive.getErrorDelta());

		SmartDashboard.putNumber("Error Delta" , drive.getErrorDelta());
		SmartDashboard.putNumber("Wheel Delta" ,  drive.getFrontRightPos() + drive.getFrontLeftPos());
		
		gearbox.scootMode();
		//gearbox.bullyMode();
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
		elevator.encoderStart();
		elevatorControl.start();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Front Left Position", (double) drive.getFrontLeftPos());
		SmartDashboard.putNumber("Front Right Position", (double) drive.getFrontRightPos());
		SmartDashboard.putNumber("LeftError", drive.leftErrorReport);
		SmartDashboard.putNumber("RightError" , drive.rightErrorReport);
		
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
		
		
		m_chooser.addObject("Left Station to Switch", "LeftStationToSwitch");
		m_chooser.addObject("Center Station to Switch", "CenterStationToSwitch");
		m_chooser.addObject("Right Station to Switch", "RightStationToSwitch");
		m_chooser.addObject("Far Left to Switch", "FarLeftToSwitch");
		m_chooser.addObject("Far Right to Switch", "FarRightToSwitch");

		m_chooser.addObject("---- Scale ----", "");
//		m_chooser.addObject("Far Left to Scale", "FarLeftToScale");
//		m_chooser.addObject("Far Right to Scale", "FarRightToScale");
		m_chooser.addObject("Far Left to Scale (wait)", "FarLeftToScaleAndWait");
		m_chooser.addObject("Far Right to Scale (wait)", "FarRightToScaleAndWait");
		
		
		
		m_chooser.addObject("Rotate Test", "RotateTest");
		m_chooser.addObject("Rotate Test Counterclockwise", "RotateTestCCW");
		m_chooser.addObject("Drive Straight Test", "DriveStraightTest");
		m_chooser.addDefault("FMS Test", "DoNothingCommand");
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
