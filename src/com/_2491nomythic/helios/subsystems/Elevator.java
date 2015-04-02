package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.elevator.RunElevator;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * The thing that lifts totes.
 */
public class Elevator extends PIDSubsystem {
	private CANTalon motorElevatorA, motorElevatorB;
	private Encoder encoder;
	private DigitalInput limitTop, limitBottom , toteCheckLeft, toteCheckRight;
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
	
	/**
	 * The thing that lifts totes.
	 */
	public Elevator() {
		super(Variables.elevatorPID_P, Variables.elevatorPID_I, Variables.elevatorPID_D);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		motorElevatorA = new CANTalon(Constants.elevatorTalonMotorAChannel);
		motorElevatorB = new CANTalon(Constants.elevatorTalonMotorBChannel);
		
		encoder = new Encoder(Constants.elevatorEncoderAChannel, Constants.elevatorEncoderBChannel, Constants.elevatorEncoderReversed, CounterBase.EncodingType.k4X);
		encoder.setDistancePerPulse(Constants.elevatorEncoderToFeet);
		this.setInputRange(Constants.elevatorMinPosition, Constants.elevatorMaxPosition);
		this.setAbsoluteTolerance(1.0);
		
		limitTop = new DigitalInput(Constants.elevatorLimitTopChannel);
		limitBottom = new DigitalInput(Constants.elevatorLimitBottomChannel);
		toteCheckLeft = new DigitalInput(Constants.elevatorToteCheckLeftChannel);
		toteCheckRight = new DigitalInput(Constants.elevatorToteCheckRightChannel);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunElevator()); //Elevator control now on buttons
	}
	
	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return encoder.getDistance();
	}
	
	protected void usePIDOutput(double output) {
		internalSet(output);
	}
	
	/**
	 * Sets the power being applied to the elevator.
	 * @param speed The power and direction being applied to the elevator.
	 */
	public void set(double speed) {
		if (usingPID) {
			this.disable();
			usingPID = false;
		}
		internalSet(speed);
	}
	
	/**
	 * The internal function so that both set and usePIDOutput don't need duplicate code
	 * @param speed
	 */
	private void internalSet(double speed) {
		
		if ((getTopSwitch() || getPosition() > Constants.elevatorMaxPosition) && speed > 0.0) {
			speed = 0.0;
		}
		else if (getBottomSwitch() && speed < 0.0) {
			speed = 0.0;
		}
		else if (getPosition() < 0.5) {
			double cap = getPosition() + 0.5;
			if(-1 * speed > cap) {
				speed = -1.0 * cap;
			}
		}
		else if (getPosition() > Constants.elevatorMaxPosition - 0.5) {
			double cap =  Constants.elevatorMaxPosition -  getPosition() + 0.5;
			if (speed > cap) {
				speed = cap;
			}
		}
		
		motorElevatorA.set(-1.0 * speed);
		motorElevatorB.set(speed);
	}
	
	/**
	 * Same as set(0).  It's just more obvious what it does.
	 */
	public void stop() {
		set(0.0);
	}
	
	/**
	 * Sends the elevator to a specific position.
	 * @param position The position to send the elevator to.
	 */
	public void setPID(double position) {
		if (!usingPID) {
			this.enable();
			usingPID = true;
		}
		currentTarget = position;
		this.setSetpoint(position);
	}
	
	/**
	 * Sends the elevator to a position specified by the number of totes.
	 * @param numTotes The number of totes to send the elevator to.
	 */
	public void setPosition(int numTotes) {
		if (numTotes >= 1 && numTotes <= 4) {
			setPID(Variables.toteHeight[numTotes]);
		}
	}
	
	/**
	 * 
	 * @return The power being applied to the elevator.
	 */
	public double get() {
		return currentSpeed;
	}
	
	/**
	 * 
	 * @return The current target the elevator is being sent to.
	 */
	public double getPID() {
		return currentTarget;
	}
	
	/**
	 * 
	 * @return Whether or not the elevator is currently using PID.
	 */
	public boolean isUsingPID() {
		return usingPID;
	}
	
	/**
	 * 
	 * @return The current speed of the elevator.
	 */
	public double getRate() {
		return encoder.getRate();
	}
	
	/**
	 * 
	 * @return The current value of the encoder.
	 */
	public Encoder getEncoder() {
		return encoder;
	}
	
	/**
	 * 
	 * @return Whether the bottom elevator limit switch is pressed.
	 */
	public boolean getBottomSwitch() {
		return !limitBottom.get();
	}
	
	/**
	 * 
	 * @return Whether the top elevator limit switch is pressed.
	 */
	public boolean getTopSwitch() {
		return !limitTop.get();
	}
	
	/**
	 * 
	 * @return Whether the left tote check limit switch is pressed.
	 */
	public boolean getToteCheckLeft() {
		return !toteCheckLeft.get();
	}
	
	/**
	 * 
	 * @return Whether the right tote check limit switch is pressed.
	 */
	public boolean getToteCheckRight() {
		return !toteCheckRight.get();
	}
	
	/**
	 * Resets the elevator encoder.
	 */
	public void resetEncoder() {
		encoder.reset();
	}
}
