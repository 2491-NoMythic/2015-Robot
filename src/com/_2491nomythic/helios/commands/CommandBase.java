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
import com._2491nomythic.helios.settings.Variables;
import com._2491nomythic.helios.subsystems.*;
import com._2491nomythic.util.components.hardware.HardwareEncoder;
import com._2491nomythic.util.components.hardware.HardwareGyro;
import com._2491nomythic.util.components.hardware.HardwareMotor;
import com._2491nomythic.util.components.interfaces.Encoder;
import com._2491nomythic.util.components.interfaces.Gyroscope;
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
		//Set up elevator
		Motor motorElevatorA = HardwareMotor.createWithCANTalon(Constants.elevatorTalonMotorAChannel);
		Motor motorElevatorB = HardwareMotor.createWithCANTalon(Constants.elevatorTalonMotorAChannel);
		Encoder elevatorEncoder = new HardwareEncoder(Constants.elevatorEncoderAChannel, Constants.elevatorEncoderBChannel, Constants.elevatorEncoderReversed, CounterBase.EncodingType.k4X, Constants.elevatorEncoderToFeet);
		LimitSwitch topElevatorLimitSwitch = new HardwareLimitSwitch(Constants.elevatorLimitTopChannel);
		LimitSwitch bottomElevatorLimitSwitch = new HardwareLimitSwitch(Constants.elevatorLimitBottomChannel);
		elevator = new Elevator(motorElevatorA, motorElevatorB, elevatorEncoder, topElevatorLimitSwitch, bottomElevatorLimitSwitch);
		
		// Set up the drivetrain
		Motor drivetrainFrontLeftMotor   = HardwareMotor.createWithCANTalon(Constants.driveTalonFrontLeftChannel);
		Motor drivetrainFrontCenterMotor = HardwareMotor.createWithCANTalon(Constants.driveTalonFrontCenterChannel);
		Motor drivetrainFrontRightMotor  = HardwareMotor.createWithCANTalon(Constants.driveTalonFrontRightChannel);
		Motor drivetrainBackLeftMotor    = HardwareMotor.createWithCANTalon(Constants.driveTalonBackLeftChannel);
		Motor drivetrainBackCenterMotor  = HardwareMotor.createWithCANTalon(Constants.driveTalonBackCenterChannel);
		Motor drivetrainBackRightMotor   = HardwareMotor.createWithCANTalon(Constants.driveTalonBackRightChannel);
		Encoder drivetrainLeftEncoder    = new HardwareEncoder(Constants.driveEncoderLeftAChannel, Constants.driveEncoderLeftBChannel, Constants.driveEncoderLeftReversed, CounterBase.EncodingType.k1X, Constants.driveEncoderToFeet);
		Encoder drivetrainCenterEncoder  = new HardwareEncoder(Constants.driveEncoderCenterAChannel, Constants.driveEncoderCenterBChannel, Constants.driveEncoderCenterReversed, CounterBase.EncodingType.k1X, Constants.driveEncoderToFeet);
		Encoder drivetrainRightEncoder   = new HardwareEncoder(Constants.driveEncoderRightAChannel, Constants.driveEncoderRightBChannel, Constants.driveEncoderRightReversed, CounterBase.EncodingType.k1X, Constants.driveEncoderToFeet);
		Gyroscope drivetrainGyro         = new HardwareGyro(Constants.gyroChannel, Variables.gyroToDegrees);
		drivetrain = new Drivetrain(drivetrainFrontLeftMotor, drivetrainFrontCenterMotor, drivetrainFrontRightMotor, drivetrainBackLeftMotor, drivetrainBackCenterMotor, drivetrainBackRightMotor, drivetrainLeftEncoder, drivetrainCenterEncoder, drivetrainRightEncoder, drivetrainGyro);
		arm = Arm.getInstance();
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
