package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX intakeMotor1;
	WPI_TalonSRX intakeMotor2;

	public void Intake() {
		intakeMotor1 = new WPI_TalonSRX(RobotMap.INTAKE_1_MOTOR_CHANNEL);
		intakeMotor2 = new WPI_TalonSRX(RobotMap.INTAKE_2_MOTOR_CHANNEL);
	}
	
	public void IntakeIn(double speed) {
		intakeMotor1.set(ControlMode.PercentOutput, speed);
		intakeMotor2.set(ControlMode.PercentOutput, speed);
	}
	
	public void IntakeOut(double speed) {
		intakeMotor1.set(ControlMode.PercentOutput, -speed);
		intakeMotor2.set(ControlMode.PercentOutput, -speed);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

