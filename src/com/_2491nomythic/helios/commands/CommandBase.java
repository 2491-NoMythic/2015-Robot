package com._2491nomythic.helios.commands;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com._2491nomythic.helios.OI;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.subsystems.*;
import com._2491nomythic.util.components.hardware.HardwareLimitSwitch;
import com._2491nomythic.util.components.hardware.HardwareMotor;
import com._2491nomythic.util.components.hardware.HardwareEncoder;
import com._2491nomythic.util.components.interfaces.Encoder;
import com._2491nomythic.util.components.interfaces.LimitSwitch;
import com._2491nomythic.util.components.interfaces.Motor;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 * 
 * @author Author
 */
public abstract class CommandBase extends Command {
	
	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Arm arm;
	protected static Elevator elevator;
	protected static Grabber grabber;
	protected static ExtraSensors extraSensors;
	protected static Camera camera;
	
	public static void init() {
		Motor motorElevatorA = HardwareMotor.createFromCANTalon(Constants.elevatorTalonMotorAChannel);
		Motor motorElevatorB = HardwareMotor.createFromCANTalon(Constants.elevatorTalonMotorAChannel);
		Encoder elevatorEncoder = new HardwareEncoder(Constants.elevatorEncoderAChannel, Constants.elevatorEncoderBChannel, Constants.elevatorEncoderReversed, CounterBase.EncodingType.k4X, Constants.elevatorEncoderToFeet);
		LimitSwitch topElevatorLimitSwitch = new HardwareLimitSwitch(Constants.elevatorLimitTopChannel);
		LimitSwitch bottomElevatorLimitSwitch = new HardwareLimitSwitch(Constants.elevatorLimitBottomChannel);
		
		drivetrain = Drivetrain.getInstance();
		arm = Arm.getInstance();
		elevator = Elevator.getInstance();
		grabber = Grabber.getInstance();
		extraSensors = ExtraSensors.getInstance();
		camera = Camera.getInstance();
		// This MUST be here. If the OI creates Commands (which it very likely
		// will), constructing it during the construction of CommandBase (from
		// which commands extend), subsystems are not guaranteed to be
		// yet. Thus, their requires() statements may grab null pointers. Bad
		// news. Don't move it.
		oi = new OI();
		oi.init();
		
		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(arm);
		SmartDashboard.putData(grabber);
		SmartDashboard.putData(elevator);
	}
	
	/**
	 * The base for all commands. All atomic commands should subclass CommandBase.
	 * CommandBase stores creates and stores each control system. To access a
	 * subsystem elsewhere in your code in your code use
	 * CommandBase.exampleSubsystem
	 * @param name The name that shows up on the SmartDashboard in association with any command created using this parameter.
	 */
	public CommandBase(String name) {
		super(name);
	}
	
	/**
	 * The base for all commands. All atomic commands should subclass CommandBase.
	 * CommandBase stores creates and stores each control system. To access a
	 * subsystem elsewhere in your code in your code use
	 * CommandBase.exampleSubsystem
	 */
	public CommandBase() {
		super();
	}
}
