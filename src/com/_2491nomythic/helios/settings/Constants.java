package com._2491nomythic.helios.settings;

public class Constants {
	// Joysticks
	public static final int ControllerOnePort = 0;
	public static final int ControllerTwoPort = 1;
	public static final int CompressorChannel = 1;
	
	// Drive
	public static final int driveTalonFrontLeftChannel = 12;
	public static final int driveTalonFrontCenterChannel = 16;
	public static final int driveTalonFrontRightChannel = 17;
	public static final int driveTalonBackLeftChannel = 10;
	public static final int driveTalonBackCenterChannel = 11;
	public static final int driveTalonBackRightChannel = 15;
	public static final int driveEncoderLeftAChannel = 0;
	public static final int driveEncoderLeftBChannel = 1;
	public static final int driveEncoderCenterAChannel = 4;
	public static final int driveEncoderCenterBChannel = 5;
	public static final int driveEncoderRightAChannel = 2;
	public static final int driveEncoderRightBChannel = 3;
	public static final boolean driveEncoderLeftReversed = false;
	public static final boolean driveEncoderCenterReversed = false;
	public static final boolean driveEncoderRightReversed = false;
	public static final double driveEncoderToFeet = 1.0;
	public static final int gyroChannel = 1;
	
	// Arm
	public static final int armTalonArmMotorLeftChannel = 14;
	public static final int armTalonArmMotorRightChannel = 19;
	public static final int ArmBrakeOnChannel = 0;
	public static final int ArmBrakeOffChannel = 1;
	public static final double ArmBrakeMaxSpeed = 10.0; // Max speed of arm to
														// allow brake to be
														// engaged
	public static final int armEncoderAChannel = 6;
	public static final int armEncoderBChannel = 7;
	public static final boolean armEncoderReversed = false;
	public static final double armEncoderToDegrees = 1.0;
	public static final double armMinPosition = 0.0;
	public static final double armMaxPosition = 180.0;
	
	// Elevator
	public static final int elevatorTalonMotorChannel = 14;
	public static final int elevatorEncoderAChannel = 8;
	public static final int elevatorEncoderBChannel = 9;
	public static final boolean elevatorEncoderReversed = false;
	public static final double elevatorEncoderToFeet = 1.0;
	public static final double elevatorMinPosition = 0.0;
	public static final double elevatorMaxPosition = 5.0;
	
	// Grabber
	public static final int grabberTalonMotorChannel = 19;
}
