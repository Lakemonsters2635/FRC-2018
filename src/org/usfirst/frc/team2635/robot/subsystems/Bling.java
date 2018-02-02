package org.usfirst.frc.team2635.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  Heavily based on Team 3574's LED system
 *  Adapted for use by 2635
 */
public class Bling extends Subsystem 
{
    //TODO: Improve buttons bindings for manual control
    //ID's to send to arduino for each lighting scene. Used for reference
    public static final int rainbowFade = 0,
                            firing = 1,
                            marchRWB = 2;
    private I2C chat;
    private int previousScene = 0;
    private int currentScene = 0;
    private int defaultScene = 0;
    
    public Bling()
    {
    	chat = new I2C(I2C.Port.kOnboard, 168);
        //DigitalModule digiMod = DigitalModule.getInstance(1);
        //chat = digiMod.getI2C(8);
        
        
        //chat.setCompatabilityMode(true);
        
    }
    
    public void set(int scene)
    {
        previousScene = currentScene;
        currentScene = scene;
        
        byte[] msg = { 0 };
        msg[0] = (byte)scene;
        byte[] resp = new byte[100];

        chat.transaction(msg, 1, resp, 0);
    }
    
    public void revert()
    {
        set(previousScene);
    }
    
    public void setDefault(int scene)
    {
        defaultScene = scene;
    }
    
    public void defaultScene()
    {
        set(defaultScene);
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
                            
}