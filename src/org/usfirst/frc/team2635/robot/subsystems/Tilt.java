package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Tilt extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid leftSolenoid;
	DoubleSolenoid rightSolenoid;
	
	public Tilt () {
		leftSolenoid = new DoubleSolenoid(RobotMap.UP_LEFT_TILT, RobotMap.DOWN_LEFT_TILT);
    	rightSolenoid = new DoubleSolenoid(RobotMap.UP_RIGHT_TILT, RobotMap.DOWN_RIGHT_TILT);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setUp() {
    	leftSolenoid.set(Value.kForward);
    	rightSolenoid.set(Value.kForward);
    }
    
    public void setDown() {
    	leftSolenoid.set(Value.kReverse);
    	rightSolenoid.set(Value.kReverse);	
    }
}

