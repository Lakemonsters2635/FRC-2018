package org.usfirst.frc.team2635.robot.subsystems;

import org.usfirst.frc.team2635.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid solenoid;

	public Grabber() {
		solenoid = new DoubleSolenoid(RobotMap.OPEN_GRABBER, RobotMap.CLOSE_GRABBER);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
   
    }
    
    public void setOpen() {
    	//solenoid.set(Value.kReverse);
    	solenoid.set(Value.kForward);
    }
    
    public void setClosed() {
    	//solenoid.set(Value.kForward);
    	solenoid.set(Value.kReverse);
    }
}

