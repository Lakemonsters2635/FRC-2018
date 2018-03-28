package org.usfirst.frc.team2635.robot.commands;

import org.usfirst.frc.team2635.robot.Robot;
import org.usfirst.frc.team2635.robot.model.SensorParams;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetTarget extends Command {
	public SensorParams visionParams;
	
    public GetTarget(SensorParams params) {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.drive);
        this.visionParams = params;
        this.setTimeout(0.25);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vision.data();
    	visionParams.x = Robot.vision.getXAngle();
    	visionParams.y = Robot.vision.getYAngle();
    	visionParams.area = Robot.vision.getArea();
    	

    	System.out.println("GetTarget... ") ;

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double x = Robot.vision.getXAngle();
        if(x == 0.0) {
           	//System.out.println("Target not acquired... ") ;

        	return false;
        }
        else {
        	

        	
        	System.out.println("Target acquired... ") ;
//        	Distance, area, yAngle, full cube in view
//        	0in, 13.55, -14.5, no
//        	25in, 11.35, -11.7, yes
//        	36in, 7, 7.35, -8.33, y
        	double yAngleWhenCubeIsAtBumper = -14.5;
        	double angleRatio =  yAngleWhenCubeIsAtBumper / visionParams.y;
        	//6.300*X + 116.0

        	visionParams.distance = (6.300 * visionParams.y + 116.0)-14;
        	//visionParams.distance = Math.abs(5.748 * visionParams.y);
        	//visionParams.distance= angleRatio * 7;
        	
        	if (visionParams.distance > 72)
        	{
        		System.out.println("Target distance is too far: " + visionParams.distance) ;
        		visionParams.distance = 0;
        	}
        	System.out.println("X: " + visionParams.x + "  Y:" + visionParams.y + "  Area:" + visionParams.area + " Distance:" + visionParams.distance) ;
            return true;       	
        }
        	

    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
