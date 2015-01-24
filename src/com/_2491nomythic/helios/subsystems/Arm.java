package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.arm.ArmPositionSet;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	private CANTalon motorLeft, motorRight;
	private Encoder encoder;
	private Solenoid brakeOn, brakeOff;
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
		motorLeft = new CANTalon(Constants.armTalonArmMotorLeftChannel);
		motorRight = new CANTalon(Constants.armTalonArmMotorRightChannel);
		
		brakeOn = new Solenoid(Constants.ArmBrakeOnChannel);
		brakeOff = new Solenoid(Constants.ArmBrakeOffChannel);
		
		encoder = new Encoder(Constants.armEncoderAChannel,
				Constants.armEncoderBChannel, Constants.armEncoderReversed,
				CounterBase.EncodingType.k4X);
		encoder.setDistancePerPulse(Constants.armEncoderToDegrees);
		this.setInputRange(Constants.armMinPosition, Constants.armMaxPosition);
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
		motorLeft.set(output);
		motorRight.set(-1.0 * output);
		currentSpeed = output;
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
	
	public void set(double speed) {
		disengageBrake();
		if (usingPID) {
			this.disable();
			usingPID = false;
		}
		motorLeft.set(speed);
		motorRight.set(-1.0 * speed);
	}
	
	public void setPID(double position) {
		disengageBrake();
		if (!usingPID) {
			this.enable();
			usingPID = true;
		}
		currentTarget = position;
		this.setSetpoint(position);
	}
	
	private void engageBrake() {
		motorLeft.set(0.0);
		motorRight.set(0.0);
		while (encoder.getRate() > Constants.ArmBrakeMaxSpeed) {
			Timer.delay(0.01);
		}
		brakeOff.set(false);
		brakeOn.set(true);
	}
	
	private void disengageBrake() {
		brakeOn.set(false);
		brakeOff.set(true);
	}
	
	public void stop() {
		engageBrake();
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
}
