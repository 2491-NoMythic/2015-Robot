package com._2491nomythic.helios.settings;

public class Variables {
	public static double armPID_P = 1.0;
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
	
	// Arm Positions
	public static double verticalArmPos = 0;
	public static double horizontalAForewardPos = 90; // to be decided
	public static double horizontalABackPos = -90;
	public static double binPickup = 80;
	// to be decided
	public static double recyclingContainerReleasePos = -90; // the position that the arm will be when the
	// recycling container touches the autozone AND may be released (this position needs to be far enough so that the arm can be pulled away)
	
	// Autonomous Distances
	public static double pickup1stBinXDistance; // May be added if needed?
	public static double pickupBinYDistance = 0.167; // to be decided
	public static double pickup2ndBinXDistance = -4.225;
	public static double driveNextToBinXDistance = 3.058;
	public static double driveNextToBinYDistance = -5.94;
	public static double pickup2ndBinYDistance;
	public static double unhookBinXDistance = 0.0;
	public static double unhookBinYDistance = 0.167;
	
	// Autonomous Drive Power Variables
	public static double pickupBinDrivePower = 0.5;
	public static double driveBackToConPlacementPos;
	public static double unhookBinPower = 0.5;
	
	//Manual Drive Checker
	public static boolean manualHasBeenUsed = false;
	
	
}