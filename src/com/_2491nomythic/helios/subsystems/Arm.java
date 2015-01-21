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
public class Arm extends PIDSubsystem {
	CANTalon armMotorLeft, armMotorRight;
	Encoder armEncoder;
    // Initialize your subsystem here
	
    public Arm() {
    	super(Variables.armPID_P, Variables.armPID_I, Variables.armPID_D);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	armMotorLeft = new CANTalon(Constants.armTalonArmMotorLeftChannel);
    	armMotorRight = new CANTalon(Constants.armTalonArmMotorRightChannel);
    	
    	armEncoder = new Encoder(Constants.armEncoderAChannel, Constants.armEncoderBChannel, Constants.armEncoderReversed, CounterBase.EncodingType.k1X);
    }
    
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return armEncoder.getDistance();
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    }
    
    protected void usePIDOutput(double output) {
    	
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
