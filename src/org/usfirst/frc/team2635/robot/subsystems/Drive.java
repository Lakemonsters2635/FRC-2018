package org.usfirst.frc.team2635.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends Subsystem {
	WPI_TalonSRX frontLeftMotor;
	WPI_TalonSRX frontRightMotor;
	WPI_TalonSRX backLeftMotor;
	WPI_TalonSRX backRightMotor;
	Joystick leftJoy;
	Joystick rightJoy;
	DifferentialDrive drive;
    
	public Drive(){
		frontLeftMotor = new WPI_TalonSRX(1);
		frontRightMotor = new WPI_TalonSRX(3);
		backLeftMotor = new WPI_TalonSRX(2);
		backRightMotor = new WPI_TalonSRX(4);
		
		backLeftMotor.follow(frontLeftMotor);
		backRightMotor.follow(frontRightMotor);
		
		drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
		
	}

	public void TankDrive(double left, double right){
		drive.tankDrive(-left, -right);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

