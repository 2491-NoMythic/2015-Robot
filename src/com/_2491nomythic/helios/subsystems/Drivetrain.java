package com._2491nomythic.helios.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	Motor frontRight, frontCenter, frontLeft, backRight, backCenter, backLeft;
	Encoder encoderLeft, encoderCenter, encoderRight;
	
	Drivetrain() {
		
	}
	
	
	
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

