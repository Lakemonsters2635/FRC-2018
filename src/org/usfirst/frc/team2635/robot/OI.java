/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick leftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
	public Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
	
	
	//LEFT BUTTONS
	//public Button grabberButtonLeft = new JoystickButton(leftStick, RobotMap.GRABBER_BUTTON);
	public Button tiltButton = new JoystickButton(leftStick, RobotMap.TILT_BUTTON);
	public Button visionButton = new JoystickButton(leftStick, RobotMap.VISION_LIGHT_TOGGLE_BUTTON);
	public Button driveModeButton = new JoystickButton(leftStick, RobotMap.DRIVE_MODE_CHANGE_BUTTON);
	public Button climbUpButton = new JoystickButton(leftStick, RobotMap.CLIMB_UP_BUTTON);
	public Button climbDownButton = new JoystickButton(leftStick, RobotMap.CLIMB_DOWN_BUTTON);
	public Button tiltToggleButton = new JoystickButton(leftStick, RobotMap.TILT_BUTTON); 
	//public Button elevatorTestButton = new JoystickButton(leftStick, RobotMap.ELEVATOR_TEST_BUTTON);
	//public Button openGrabberTestButton = new JoystickButton(leftStick, RobotMap.OPEN_GRABBER_TEST_BUTTON);
	
	//RIGHT BUTTONS
	public Button grabberButtonRight = new JoystickButton(rightStick, RobotMap.GRABBER_BUTTON);
	public Button gearShiftButton = new JoystickButton(rightStick, RobotMap.GEAR_SHIFT_BUTTON);
	public Button elevatorUpButton = new JoystickButton(rightStick, RobotMap.ELEVATOR_UP_BUTTON);
	public Button elevatorDownButton = new JoystickButton(rightStick, RobotMap.ELEVATOR_DOWN_BUTTON);
	public Button intakeToggleButton = new JoystickButton(rightStick, RobotMap.INTAKE_TOGGLE_BUTTON);
	//public Button returnButton = new JoystickButton(rightStick, RobotMap.RETURN_BUTTON);
	public Button navxRotateButton = new JoystickButton(rightStick, RobotMap.NAVX_ROTATE_BUTTON);
	public Button expressElevatorUpButton = new JoystickButton(rightStick, RobotMap.ALTERNATE_ELEVATOR_UP_BUTTON);
	public Button expressElevatorDownButton = new JoystickButton(rightStick, RobotMap.ALTERATE_ELEVATOR_DOWN_BUTTON);

	//public Button closeGrabberTestButton = new JoystickButton(rightStick, RobotMap.CLOSE_GRABBER_TEST_BUTTON);
	
	
	
	
	
	
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
