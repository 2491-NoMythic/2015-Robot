package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.elevator.Elevate;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private CANTalon motorElevator;
	private Encoder encoder;
	private DigitalInput limitTop, limitBottom, toteCheckLeft, toteCheckRight;
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
		super(Variables.elevatorPID_P, Variables.elevatorPID_I,
				Variables.elevatorPID_D);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		motorElevator = new CANTalon(Constants.elevatorTalonMotorChannel);
		
		encoder = new Encoder(Constants.elevatorEncoderAChannel,
				Constants.elevatorEncoderBChannel,
				Constants.elevatorEncoderReversed, CounterBase.EncodingType.k4X);
		encoder.setDistancePerPulse(Constants.elevatorEncoderToFeet);
		this.setInputRange(Constants.elevatorMinPosition,
				Constants.elevatorMaxPosition);
		
		limitTop = new DigitalInput(Constants.elevatorLimitTopChannel);
		limitBottom = new DigitalInput(Constants.elevatorLimitBottomChannel);
		toteCheckLeft = new DigitalInput(Constants.elevatorToteCheckLeftChannel);
		toteCheckRight = new DigitalInput(Constants.elevatorToteCheckRightChannel);
	} 
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Elevate());
	}
	
	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return encoder.getDistance();
	}
	
	protected void usePIDOutput(double output) {
		if (!limitTop.get()) {
			if (output >= 0) {
				motorElevator.set(0);
				currentSpeed = 0;
			}
			else if (output < 0) {
				motorElevator.set(output);
				currentSpeed = output;
			}
		}
		else if (!limitBottom.get()) {
			if (output > 0) {
				motorElevator.set(output);
				currentSpeed = output;
			}
			else if (output <= 0) {
				motorElevator.set(0);
				currentSpeed = 0;
			}
		}
		else {
			motorElevator.set(output);
			currentSpeed = output;
		}
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
	
	public void set(double speed) {
		if (usingPID) {
			this.disable();
			usingPID = false;
		}
		SmartDashboard.putBoolean("Top limit switch", getTopSwitch());
		SmartDashboard.putBoolean("Bottom limit switch", getBottomSwitch());
		if (!limitTop.get()) {
			if (speed >= 0.0) {
				motorElevator.set(0.0);
			}
			else {
				motorElevator.set(speed);
			}
		}
		else if (!limitBottom.get()) {
			if (speed > 0.0) {
				motorElevator.set(speed);
			}
			else {
				motorElevator.set(0.0);
			}
		}
		else {
			motorElevator.set(speed);
		}
	}
	
	public void setPID(double position) {
		if (!usingPID) {
			this.enable();
			usingPID = true;
		}
		currentTarget = position;
		this.setSetpoint(position);
	}
	
	public void setPosition(int numTotes){
		if(numTotes >= 1 && numTotes <= 4){
			setPID(Variables.toteHeight[numTotes]);
		}
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
	
	public Encoder getEncoder() {
		return encoder;
	}
	
	public boolean getBottomSwitch() {
		return !limitBottom.get();
	}
	
	public boolean getTopSwitch() {
		return !limitTop.get();
	}
	public void resetEncoder() {
		encoder.reset();
	}
}
