package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

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
    	
    	//FHE:TODO One Encoder
    	climbMotor1 = new WPI_TalonSRX(RobotMap.CLIMBER1_CHANNEL);
    	climbMotor2 = new WPI_TalonSRX(RobotMap.CLIMBER2_CHANNEL);
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

