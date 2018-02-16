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
	public static final int RETURN_BUTTON = 10;
	public static final int INTAKE_BUTTON = 11;
	public static final int NAVX_ROTATE_BUTTON = 7;
	
	//PCM
	public static final int OPEN_GRABBER = 0;
	public static final int CLOSE_GRABBER = 1;
	
	public static final int UP_TILT_PCM_CHANNEL = 2;
	public static final int DOWN_TILT_PCM_CHANNEL= 3;
	public static final int GEARBOX_PCM_CHANNEL= 7;


	

	public static final int FRONT_RIGHT_DRIVE_CHANNEL = 1;
	public static final int BACK_RIGHT_DRIVE_CHANNEL = 2;
	public static final int FRONT_LEFT_DRIVE_CHANNEL = 3;
	public static final int BACK_LEFT_DRIVE_CHANNEL = 4;
	
	public static final int CLIMBER1_CHANNEL = 8;
	public static final int CLIMBER2_CHANNEL = 9;
	
	public static final int ELEVATOR_UPPER_MOTOR_CHANNEL = 5;
	public static final int ELEVATOR_LOWER_MOTOR1_CHANNEL = 6;
	public static final int ELEVATOR_LOWER_MOTOR2_CHANNEL = 7;
	
	public static final int ELEVATOR_GROUND_LOWER_HEIGHT = 0; //Actually will be 0
	public static final int ELEVATOR_GROUND_UPPER_HEIGHT = 0;
	//ALL FAKE VALUES VVV
	public static final int ELEVATOR_SWITCH_LOWER_HEIGHT = 0;
	public static final int ELEVATOR_SWITCH_UPPER_HEIGHT = 0;
	public static final int ELEVATOR_SCALE_LOWER_HEIGHT = 0;
	public static final int ELEVATOR_SCALE_UPPER_HEIGHT = 0;
	public static final int ELEVATOR_CLIMB_LOWER_HEIGHT = 0;
	public static final int ELEVATOR_CLIMB_UPPER_HEIGHT = 0;
	public static final int ELEVATOR_TOLERANCE = 0;
	//ALL FAKE VALUES ^^^
	
	public static final int ELEVATOR_VELOCITY = 100;
	
	public static final int INTAKE_1_MOTOR_CHANNEL = 10;
	public static final int INTAKE_2_MOTOR_CHANNEL = 11;
	
	
	public static final int MAST_LIMIT_SWITCH_IO_CHANNEL = 0;
	public static final int ELEVATOR_BOTTOM_LIMIT_SWITCH_IO_CHANNEL = 1;
	public static final int ELEVATOR_TOP_LIMIT_SWITCH_IO_CHANNEL = 2;
	
	//TODO change these vaules
	public static final double WHEEL_RADIUS_INCHES = 3;
	public static final double ROBOT_LENGTH = 39; //includes bumpers
	public static final double ROBOT_WIDTH = 34.75; //includes bumpers
	
	//2017 BUNNYBOT
//	public static final double WHEEL_SEPARATION_INCHES = 24;
//	public static double MOTION_MAGIC_P = 5;
//	public static double MOTION_MAGIC_I = 0.01;
//	public static double MOTION_MAGIC_D = 50;
//	public static double MOTION_MAGIC_F = 1.5;
	//2017 COMP BOT
	public static double MOTION_MAGIC_P = 10.0;
	public static double MOTION_MAGIC_I = 0.0;
	public static double MOTION_MAGIC_D = 0.0;
	public static double MOTION_MAGIC_F = 1.5;
	
	//public static double TURN_CONSTANT = 
	public static final double WHEEL_SEPARATION_INCHES = 25;
	
	
	
	public static final int MOTION_MAGIC_CRUISE_VELOCITY = 500;
	public static final int APPROACH_SCALE_VELOCITY = 250;
	
	public static double MOTION_MAGIC_DISTANCE = 3000;
	public static double WHEEL_DIAMETER = 6;
	
	public static double ERRORTOLERANCE = 6;

	public static boolean VELOCITYDRIVEMODE = false;
	
	//2017 BOT
	public static final int ENCODER_COUNTS_PER_REVOLUTION = 1000;
	
	//2018 BOT
	//public static final int ENCODER_COUNTS_PER_REVOLUTION = 4096/3;
	
	public static final double AUTO_DRIVE_VELOCITY = 750;
	public static final double AUTO_TURN_VELOCITY = 500;
	public static final int AUTO_DRIVE_ACCELERATION = 250;
	public static final int AUTO_TURN_ACCELERATION = 500;
	
	public static final int AUTO_WALL_TO_SWITCH = 102;
	public static final int AUTO_FWD1 = 50;
	public static final int AUTO_FWD2 = 52;
	public static final int CENTER_AUTO_TRANSLATE_FWD = 50;
	public static final int OUTSIDE_OPPOSITE_AUTO_TRANSLATE_FWD = 100;
	public static final int OUTSIDE_SAME_AUTO_TRANSLATE_FWD = 20;
	
	
}
