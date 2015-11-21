package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.grabber.RunGrabber;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.components.interfaces.Motor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The hooks at the end of the arm.
 */
public class Grabber extends Subsystem {
	private Motor motor;
	private CANTalon motor; //Where motors,...
	private double currentSpeed = 0.0; //variables,...
	private static Grabber instance; //or any other objects are set up
    
	public static Grabber getInstance() { /*This constructor only allows one instance of the Grabber*/
		if (instance == null) {
			instance = new Grabber();
		}
		return instance;
	}
	
	public Grabber() {
		motor = new CANTalon(Constants.grabberTalonMotorChannel);
	}
	
	/**
	 * The hooks at the end of the arm.
	 */
	public Grabber(Motor motor) {
		this.motor = motor;
	}
	
	/**
	 * Sets the power of the grabber.
	 * @param speed The power and direction to be applied to the grabber.
	 */
	public void set(double speed) {/*After the set up code for the class we make methods*/
		currentSpeed = speed;
		motor.setSpeed(speed);
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

