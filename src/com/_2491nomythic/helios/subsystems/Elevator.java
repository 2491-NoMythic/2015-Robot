package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private static CANTalon motorElevator;
	private static Encoder encoder;
	private static Elevator instance;
	private boolean usingPID = false;
	private double currentSpeed = 0.0;
	private double currentTarget = 0.0;

	// Initialize your subsystem here

	public static Elevator getInstance() {
		if (instance == null) {
			instance = new Elevator();
		}
		return instance;
	}

	public Elevator() {
		super(Variables.elevatorPID_P, Variables.elevatorPID_I, Variables.elevatorPID_D);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		motorElevator = new CANTalon(Constants.elevatorTalonMotorChannel);
		
		encoder = new Encoder(Constants.elevatorEncoderAChannel, Constants.elevatorEncoderBChannel, Constants.elevatorEncoderReversed, CounterBase.EncodingType.k1X);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return encoder.getDistance();
	}

	protected void usePIDOutput(double output) {
		motorElevator.set(output);
		currentSpeed = output;
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
}
