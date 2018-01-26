/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2635.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//JOYSTICKS
	public static final int RIGHT_JOYSTICK = 1;
	public static final int LEFT_JOYSTICK = 0;
	//BUTTONS
	public static final int DRIVE_MODE_CHANGE_BUTTON = 7;
	public static final int VISION_LIGHT_TOGGLE_BUTTON = 10;
	public static final int GEAR_SHIFT_BUTTON = 4;
	public static final int GRABBER_BUTTON = 1;
	public static final int ELEVATOR_UP_BUTTON = 3;
	public static final int ELEVATOR_DOWN_BUTTON = 2;
	public static final int INTAKE_TOGGLE_BUTTON = 5;
	public static final int CLIMB_UP_BUTTON = 3;
	public static final int CLIMB_DOWN_BUTTON = 2;
	public static final int TILT_BUTTON = 4;
	
	//PCM
	public static final int OPEN_GRABBER = 1;
	public static final int CLOSE_GRABBER = 2;
	
	public static final int UP_LEFT_TILT = 3;
	public static final int DOWN_LEFT_TILT = 4;
	public static final int UP_RIGHT_TILT = 5;
	public static final int DOWN_RIGHT_TILT = 6;
	
	
	//TODO change these vaules
	public static final double WHEEL_RADIUS_INCHES = 3;
	
	
	//2017 BUNNYBOT
//	public static final double WHEEL_SEPARATION_INCHES = 24;
//	public static double MOTION_MAGIC_P = 5;
//	public static double MOTION_MAGIC_I = 0.01;
//	public static double MOTION_MAGIC_D = 50;
//	public static double MOTION_MAGIC_F = 1.5;
	//2017 COMP BOT
	public static double MOTION_MAGIC_P = 5;
	public static double MOTION_MAGIC_I = 0.008;
	public static double MOTION_MAGIC_D = 0;
	public static double MOTION_MAGIC_F = 1.5;
	
	public static final double WHEEL_SEPARATION_INCHES = 25;
	public static int MOTION_MAGIC_ACCELERATION = 250;
	public static int MOTION_MAGIC_CRUISE_VELOCITY = 250;
	
	public static double MOTION_MAGIC_DISTANCE = 3000;
	public static double WHEEL_DIAMETER = 6;
	
	public static double ERRORTOLERANCE = 10;

	public static boolean VELOCITYDRIVEMODE = true;

	
	public static final double AUTO_DRIVE_VELOCITY = 250;
	public static final double AUTO_TURN_VELOCITY = 250;
}
