package com._2491nomythic.helios.subsystems;

import com._2491nomythic.commands.helios.grabber.RunGrabber;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
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
	
	public void set(double speed) {
		currentSpeed = speed;
		motor.set(speed);
	}
	
	public double get() {
		return currentSpeed;
	}
	
	public void stop() {
		set(0.0);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new RunGrabber());
    }
}

