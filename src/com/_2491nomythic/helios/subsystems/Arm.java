package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.arm.ArmPositionSet;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	private CANTalon motor;
	private Encoder encoder;
	private DigitalInput hallEffectSensor;
	private static Arm instance;
	private boolean usingPID = false;
	private double currentSpeed = 0.0;
	private double currentTarget = 0.0;
	
	// Initialize your subsystem here
	
	public static Arm getInstance() {
		if (instance == null) {
			instance = new Arm();
		}
		return instance;
	}
	
	private Arm() {
		super(Variables.armPID_P, Variables.armPID_I, Variables.armPID_D);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		motor = new CANTalon(Constants.armTalonChannel);
		
		encoder = new Encoder(Constants.armEncoderAChannel, Constants.armEncoderBChannel, Constants.armEncoderReversed, CounterBase.EncodingType.k4X);
		encoder.setDistancePerPulse(Constants.armEncoderToDegrees);
		this.setInputRange(Constants.armMinPosition, Constants.armMaxPosition);
		this.setAbsoluteTolerance(5.0);
		
		hallEffectSensor = new DigitalInput(Constants.armHallEffectSensorChannel);
	}
	
	public void initDefaultCommand() {
		
		// Set the default command for a subsystem here.
		setDefaultCommand(new ArmPositionSet());
	}
	
	protected double returnPIDInput() {
		return encoder.getDistance();
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
	}
	
	protected void usePIDOutput(double output) {
		motor.set(-1.0 * output);
		currentSpeed = output;
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
	
	public void set(double speed) {
		if (usingPID) {
			this.disable();
			usingPID = false;
		}
		motor.set(-1.0 * speed);
	}
	
	public void setPID(double position) {
		if (!usingPID) {
			this.enable();
			usingPID = true;
		}
		currentTarget = position;
		this.setSetpoint(position);
	}
	
	public void stop() {
		set(0.0);
	}
	
	public double get() {
		return currentSpeed;
	}
	
	public double getPID() {
		return currentTarget;
	}
	
	public boolean isUsingPID() {
		return usingPID;
	}
	
	public double getERate() {
		return encoder.getRate();
	}
	
	public void resetEncoder() {
		encoder.reset();
	}
	
	public Encoder getEncoder() {
		return encoder;
	}
	
	public boolean getHallEffectSensor() {
		return !hallEffectSensor.get();
	}
}
