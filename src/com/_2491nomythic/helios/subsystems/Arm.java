package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.arm.ArmPositionSet;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Arm that picks up recycling containers.
 * Does not include the hook
 */
public class Arm extends Subsystem {
	private CANTalon motor;
	private Encoder encoder;
	private DigitalInput hallEffectSensor;
	private static Arm instance;
	private boolean usingPID = false;
	private double currentSpeed = 0.0;
	private double currentTarget = 0.0;
	private double maxPIDSpeed = 0.5;
	private double maxAcceleration = 0.05;
	private PIDController controller;
	private PIDOutput output = new PIDOutput() {

        public void pidWrite(double output) {
            usePIDOutput(output);
        }
    };
    private PIDSource source = new PIDSource() {

        public double pidGet() {
            return returnPIDInput();
        }
    };
	
	// Initialize your subsystem here
	
	public static Arm getInstance() {
		if (instance == null) {
			instance = new Arm();
		}
		return instance;
	}
	
	/**
	 * The Arm that picks up recycling containers.
	 * Does not include the hook
	 */
	private Arm() {
		controller = new PIDController(Variables.armPID_P, Variables.armPID_I, Variables.armPID_D, source, output);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		motor = new CANTalon(Constants.armTalonChannel);
		
		encoder = new Encoder(Constants.armEncoderAChannel, Constants.armEncoderBChannel, Constants.armEncoderReversed, CounterBase.EncodingType.k4X);
		encoder.setDistancePerPulse(Constants.armEncoderToDegrees);
		controller.setInputRange(Constants.armMinPosition, Constants.armMaxPosition);
		controller.setAbsoluteTolerance(1.0);
		
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
		if (Math.abs(output) > maxPIDSpeed) {
			if (output > 0) {
				output = maxPIDSpeed;
			}
			else {
				output = -1 * maxPIDSpeed;
			}
		}
		
		if (Math.abs(output - currentSpeed) > maxAcceleration) {
			if (output > currentSpeed) {
				output = currentSpeed + maxAcceleration;
			}
			else {
				output = currentSpeed - maxAcceleration;
			}
		}
		motor.set(-1.0 * output);
		currentSpeed = output;
	}
	
	/**
	 * Sets the motor on the arm to a certain power.
	 * @param speed The power at which to set the motor.
	 */
	public void set(double speed) {
		if (usingPID) {
			this.disable();
			usingPID = false;
		}
		if (Math.abs(speed - currentSpeed) > maxAcceleration) {
			if (speed > currentSpeed) {
				speed = currentSpeed + maxAcceleration;
			}
			else {
				speed = currentSpeed - maxAcceleration;
			}
		}
		motor.set(-1.0 * speed);
		currentSpeed = speed;
	}
	
	/**
	 * Sets the position of the arm using PID.
	 * @param position The position to set the arm to.
	 */
	public void setPID(double position) {
		if (!usingPID) {
			this.enable();
			usingPID = true;
		}
		currentTarget = position;
		controller.setSetpoint(position);
	}
	
	/**
	 * Sets the max speed the robot uses for the arm when using PID.
	 * @param speed The top speed of the arm when using PID.
	 */
	public void setMaxPIDSpeed(double speed) {
		maxPIDSpeed = speed;
	}
	
	/**
	 * Set the maximum the arm can accelerate by
	 * @param max
	 */
	public void setMaxAccleration(double max) {
		maxAcceleration = max;
	}
	
	/**
	 * Stops the arm motor (but lets the arm fall).
	 */
	public void stop() {
		currentSpeed = 0.0;
		motor.set(0.0);
	}
	
	/**
	 * 
	 * @return The last speed that was set to the arm motor
	 */
	public double get() {
		return currentSpeed;
	}
	
	/**
	 * 
	 * @return The PID target tht was set most recently.
	 */
	public double getPID() {
		return currentTarget;
	}
	
	/**
	 * 
	 * @return If the arm is using PID at the moment
	 */
	public boolean isUsingPID() {
		return usingPID;
	}
	
	/**
	 * 
	 * @return the rate the arm is moving at in degrees per second.
	 */
	public double getRate() {
		return encoder.getRate();
	}
	
	/**
	 * Resets the arm encoder. 
	 */
	public void resetEncoder() {
		encoder.reset();
	}
	
	/**
	 * 
	 * @return The current value of the arm encoder.
	 */
	public Encoder getEncoder() {
		return encoder;
	}
	
	public void enable() {
		usingPID = true;
		controller.enable();
	}
	
	public void disable() {
		controller.reset();
		usingPID = false;
	}
	
	public boolean onTarget() {
		return controller.onTarget();
	}
	
	public double getPosition() {
		return returnPIDInput();
	}
	
	/**
	 * 
	 * @return The hall effect sensor.
	 */
	public DigitalInput getHallEffectSensor() {
		return hallEffectSensor;
	}
	
	/**
	 * 
	 * @return Whether the hall effect sensor has detected something.
	 */
	public boolean getHallEffectSensorValue() {
		return !hallEffectSensor.get();
	}
}
