package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LimitSwitch extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DigitalInput limitSwitch; 

	public LimitSwitch() {
		limitSwitch = new DigitalInput(RobotMap.MAST_LIMIT_SWITCH_IO_CHANNEL); 
	}

	public boolean get() {
		boolean result = false;
		try {
			result = !limitSwitch.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

