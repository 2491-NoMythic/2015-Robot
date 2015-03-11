package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.grabber.RunGrabber;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The hooks at the end of the arm.
 */
public class Grabber extends Subsystem {
	private CANTalon motor;
	private double currentSpeed = 0.0;
	private static Grabber instance;
    
	public static Grabber getInstance() {
		if (instance == null) {
			instance = new Grabber();
		}
		return instance;
	}
	
	private Grabber() {
		motor = new CANTalon(Constants.grabberTalonMotorChannel);
	}
	
	/**
	 * Sets the power of the grabber.
	 * @param speed The power and direction to be applied to the grabber.
	 */
	public void set(double speed) {
		currentSpeed = speed;
		motor.set(speed);
	}
	
	/**
	 * 
	 * @return The current power being applied to the grabber.
	 */
	public double get() {
		return currentSpeed;
	}
	
	/**
	 * Stops the grabber.
	 */
	public void stop() {
		set(0.0);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new RunGrabber());
    }
}

