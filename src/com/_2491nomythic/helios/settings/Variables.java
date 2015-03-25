package com._2491nomythic.helios.settings;

/**
 * A general purpose directory of variables referenced by commands and subsystems.
 */
public class Variables {
	public static double armPID_P = 0.05;
	public static double armPID_I = 0.0;
	public static double armPID_D = 0.0;
	public static double elevatorPID_P = 1.0;
	public static double elevatorPID_I = 0.0;
	public static double elevatorPID_D = 0.0;
	public static double drivexPID_P = 1.0;
	public static double drivexPID_I = 0.0;
	public static double drivexPID_D = 0.0;
	public static double driveyPID_P = 1.0;
	public static double driveyPID_I = 0.0;
	public static double driveyPID_D = 0.0;
	
	// Tote Height Integers
	public static double toteHeight[] = { 0.666667, 2.05, 3.058333, 4.066667 };
	public static int elevatorTarget = 0;
	public static boolean platformStatus = false;
	public static boolean underLipStatus = false;
	public static double underLipDistance = 0.16667;
	
	public static double gyroToDegrees = 0.0015;
	
	
	// Autonomous
	public static double pickup1stBinXDistance; // May be added if needed?
	public static double pickupBinYDistance = 0.167; // to be decided
	public static double pickup2ndBinXDistance = -4.225;
	public static double driveNextToBinXDistance = 3.058;
	public static double driveNextToBinYDistance = -5.94;
	public static double pickup2ndBinYDistance;
	public static double unhookBinXDistance = 0.0;
	public static double unhookBinYDistance = 0.167;
	public static double pickUpBinFromStepPosition = 76;
	public static double holdBinDistance = 0.0;
	public static double pickUpBinPosTwoToteAuto = 74.51;
	
	// Autonomous Drive Power Variables
	public static double pickupBinDrivePower = 0.5;
	public static double driveBackToConPlacementPos;
	public static double unhookBinPower = 0.5;
	
	//Manual Drive Checker
	public static boolean manualHasBeenUsed = false;
	
	//Variable for DriveWithArm class
	public static double armCompensationMultiplier = -1;
	
	// Elevator speed multiplier
	public static double elevatorMultiplier = 1;
}