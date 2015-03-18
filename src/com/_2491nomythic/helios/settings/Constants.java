package com._2491nomythic.helios.settings;

public class Constants {
	// Roborio directories
	public static final String homeDirectory = "/home/lvuser/";
	public static final String recordedFileExtension = ".hmi"; //Helios Movement Instructions
	
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
	public static final int driveEncoderLeftAChannel = 18;
	public static final int driveEncoderLeftBChannel = 14;
	public static final int driveEncoderCenterAChannel = 2;
	public static final int driveEncoderCenterBChannel = 13;
	public static final int driveEncoderRightAChannel = 0;
	public static final int driveEncoderRightBChannel = 1;
	public static final boolean driveEncoderLeftReversed = false;
	public static final boolean driveEncoderCenterReversed = false;
	public static final boolean driveEncoderRightReversed = false;
	public static final double driveEncoderToFeet = 0.00598740738248483559836600606686;
	public static final int gyroChannel = 1;
	public static final double centerMotorMultiplier = 1.17;
	
	// Arm
	public static final int armTalonChannel = 18;
	public static final int ArmBrakeOnChannel = 0;
	public static final int ArmBrakeOffChannel = 1;
	public static final int armEncoderAChannel = 4;
	public static final int armEncoderBChannel = 5;
	public static final boolean armEncoderReversed = true;
	public static final double armEncoderToDegrees = 0.23076923076923076923076923076923; // 0.4 for helios if we don't change the gearing
	public static final double armMinPosition = 0.0;
	public static final double armMaxPosition = 180.0;
	public static final int armHallEffectSensorChannel = 3;
	public static final double armLength = 4.833;
	
	// Elevator
	public static final int elevatorTalonMotorAChannel = 14;
	public static final int elevatorTalonMotorBChannel = 19;
	public static final int elevatorEncoderAChannel = 15;
	public static final int elevatorEncoderBChannel = 16;
	public static final boolean elevatorEncoderReversed = false;
	public static final double elevatorEncoderToFeet = 1.0;
	public static final double elevatorMinPosition = 0.0;
	public static final double elevatorMaxPosition = 5.0;
	public static final int elevatorLimitTopChannel = 8;
	public static final int elevatorLimitBottomChannel = 9;
	public static final int elevatorToteCheckLeftChannel = 11;
	public static final int elevatorToteCheckRightChannel = 12;
	
	// Grabber
	public static final int grabberTalonMotorChannel = 13;
	
	// Null (zero value) Driving Distances
	public static final double nullX = 0.0;
	public static final double nullY = 0.0;
	
	//Square root of three for speed buttons
	public static final double sqrt3 = Math.sqrt(3);
}
