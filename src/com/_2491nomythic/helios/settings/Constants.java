package com._2491nomythic.helios.settings;

public class Constants {
	//Joysticks
	public static final int ControllerOnePort = 1;
	public static final int ControllerTwoPort = 2;
	
	//Drive
	public static final int driveTalonFrontLeftChannel = 0;
	public static final int driveTalonFrontCenterChannel = 0;
	public static final int driveTalonFrontRightChannel = 0;
	public static final int driveTalonBackLeftChannel = 0;
	public static final int driveTalonBackCenterChannel = 0;
	public static final int driveTalonBackRightChannel = 0;
	public static final int driveEncoderLeftAChannel = 0;
	public static final int driveEncoderLeftBChannel = 0;
	public static final int driveEncoderCenterAChannel = 0;
	public static final int driveEncoderCenterBChannel = 0;
	public static final int driveEncoderRightAChannel = 0;
	public static final int driveEncoderRightBChannel = 0;
	public static final boolean driveEncoderLeftReversed = false;
	public static final boolean driveEncoderCenterReversed = false;
	public static final boolean driveEncoderRightReversed = false;
	public static final double driveEncoderToFeet = 1.0;
	
	
	//Arm
	public static final int armTalonArmMotorLeftChannel = 0;
	public static final int armTalonArmMotorRightChannel = 0;
	public static final int ArmBrakeOnChannel = 0;
	public static final int ArmBrakeOffChannel = 0;
	public static final double ArmBrakeMaxSpeed = 10.0; //Max speed of arm to allow brake to be engaged
	public static final int armEncoderAChannel = 0;
	public static final int armEncoderBChannel = 0;
	public static final boolean armEncoderReversed = false;
	public static final double armEncoderToDegrees = 1.0;
	public static final double armMinPosition = 0.0;
	public static final double armMaxPosition = 180.0;
	
	//Elevator
	public static final int elevatorTalonMotorChannel = 0;
	public static final int elevatorEncoderAChannel = 0;
	public static final int elevatorEncoderBChannel = 0;
	public static final boolean elevatorEncoderReversed = false;
	public static final double elevatorEncoderToFeet = 1.0;
	public static final double elevatorMinPosition = 0.0;
	public static final double elevatorMaxPosition = 5.0;
}
