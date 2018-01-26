package org.usfirst.frc.team2635.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	
	NetworkTableEntry tx;
	NetworkTableEntry ty;
	NetworkTableEntry ta;
	NetworkTableEntry ts;
	NetworkTableEntry camMode;
	NetworkTableEntry ledMode;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Vision(){
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		tx = table.getEntry("tx");
		ty = table.getEntry("ty");
		ta = table.getEntry("ta");
		ts = table.getEntry("ts");
		camMode = table.getEntry("camMode");
		ledMode = table.getEntry("ledMode");
		
		//Turn off LED
		ledMode.setDouble(1);
	}
	
	public void driveMode(){
		//Turn off LED
		ledMode.setDouble(1);
		//Set cam to drive mode
		camMode.setDouble(1);
	}
	
	public void ledOff(){
		ledMode.setDouble(1);
	}
	
	public void ledOn(){
		ledMode.setDouble(0);
	}
	
	public void data() {
		//Set cam mode to vision processing
		camMode.setDouble(0);
		//Turn on LED
		ledMode.setDouble(0);
		//Get x and y angles
		double x = tx.getDouble(0);
		double y = ty.getDouble(0);
		//Get area of target
		double area = ta.getDouble(0);
		//Get skew of target
		double skew = ts.getDouble(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

