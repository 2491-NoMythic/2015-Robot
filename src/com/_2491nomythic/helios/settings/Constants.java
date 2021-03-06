package com._2491nomythic.helios.settings;

/**
 * A directory of constants referenced in other commands and subsystems.
 */
public class Constants {
	// Roborio directories
	public static final String homeDirectory = "/home/lvuser/";
	public static final String recordedFileExtension = ".hmi"; //Helios Movement Instructions
	
	// Joysticks
	public static final int ControllerOnePort = 0;
	public static final int ControllerTwoPort = 1;
	
	// Talons
	public static final int driveTalonFrontLeftChannel = 12;
	public static final int driveTalonFrontCenterChannel = 16;
	public static final int driveTalonFrontRightChannel = 17;
	public static final int driveTalonBackLeftChannel = 10;
	public static final int driveTalonBackCenterChannel = 11;
	public static final int driveTalonBackRightChannel = 15;
	public static final int armTalonChannel = 18;
	public static final int elevatorTalonMotorAChannel = 14;
	public static final int elevatorTalonMotorBChannel = 19;
	public static final int grabberTalonMotorChannel = 13;
	
	// Digital IO, >= 10 means not currently used
	public static final int driveEncoderLeftAChannel = 14;
	public static final int driveEncoderLeftBChannel = 15;
	public static final int driveEncoderCenterAChannel = 12;
	public static final int driveEncoderCenterBChannel = 13;
	public static final int driveEncoderRightAChannel = 0;
	public static final int driveEncoderRightBChannel = 1;
	public static final int armEncoderAChannel = 4;
	public static final int armEncoderBChannel = 5;
	public static final int armHallEffectSensorChannel = 11;
	public static final int elevatorEncoderAChannel = 6;
	public static final int elevatorEncoderBChannel = 7;
	public static final int elevatorLimitTopChannel = 8;
	public static final int elevatorLimitBottomChannel = 9;
	public static final int elevatorToteCheckLeftChannel = 18;
	public static final int elevatorToteCheckRightChannel = 19;
	
	// Analog IO
	public static final int gyroChannel = 1;
	
	// Other things for Hardware
	public static final boolean driveEncoderLeftReversed = false;
	public static final boolean driveEncoderCenterReversed = false;
	public static final boolean driveEncoderRightReversed = false;
	public static final double driveEncoderToFeet = 0.00598740738248483559836600606686;
	public static final double centerMotorMultiplier = 1.17;
	public static final boolean armEncoderReversed = true;
	public static final double armEncoderToDegrees = 0.23076923076923076923076923076923; // 0.4 for helios if we don't change the gearing
	public static final double armMinPosition = -90.0;
	public static final double armMaxPosition = 90.0;
	public static final double armLength = 4.833;
	public static final boolean elevatorEncoderReversed = false;
	public static final double elevatorEncoderToFeet = 0.00265775786231219618505436443707;
	public static final double elevatorMinPosition = 0.0;
	public static final double elevatorMaxPosition = 5.0;	
	
	// Other things for software
	// Null (zero value) Driving Distances
	public static final double nullX = 0.0;
	public static final double nullY = 0.0;
	// Square root of three for speed buttons
	public static final double sqrt3 = Math.sqrt(3);	
	// Autonomous Two Bin Position Distance Away From Recycling Container (ft)
	public static final double autonomousTwoBinPositionAwayFromRecContainer = 3.480298764; //or .519701236 ft away from gaffers tape
	public static final double upOneToteTime = 1.75;
}
