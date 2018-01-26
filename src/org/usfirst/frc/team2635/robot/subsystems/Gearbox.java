package org.usfirst.frc.team2635.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gearbox extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid gearShiftSolenoid;
	
	public Gearbox(){
		gearShiftSolenoid = new Solenoid(7);
	}
	
	public void scootMode(){
		gearShiftSolenoid.set(false);
	}
	
	public void bullyMode(){
		gearShiftSolenoid.set(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

