package org.usfirst.frc.team2635.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	WPI_TalonSRX climbMotor1;
	WPI_TalonSRX climbMotor2;
    public Climber(){
    	climbMotor1 = new WPI_TalonSRX(8);
    	climbMotor2 = new WPI_TalonSRX(9);
    }
    
    public void climbUp(){
    	climbMotor1.set(ControlMode.PercentOutput, 1);
    	climbMotor2.set(ControlMode.PercentOutput, 1);
    }
    
    public void climbDown(){
    	climbMotor1.set(ControlMode.PercentOutput, -1);
    	climbMotor2.set(ControlMode.PercentOutput, -1);
    }
    
    public void climbStop(){
    	climbMotor1.stopMotor();
    	climbMotor2.stopMotor();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

