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
	DoubleSolenoid tiltSolenoid;

	
	public Tilt () {
		tiltSolenoid = new DoubleSolenoid(RobotMap.UP_TILT_PCM_CHANNEL, RobotMap.DOWN_TILT_PCM_CHANNEL);
    
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setUp() {
    	tiltSolenoid.set(Value.kForward);

    }
    
    public void setDown() {
    	tiltSolenoid.set(Value.kReverse);
    }
}

