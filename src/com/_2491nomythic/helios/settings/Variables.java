package com._2491nomythic.helios.settings;

public class Variables {
	public static double armPID_P = 1.0;
	public static double armPID_I = 0.0;
	public static double armPID_D = 0.0;
	public static double elevatorPID_P = 1.0;
	public static double elevatorPID_I = 0.0;
	public static double elevatorPID_D = 0.0;
	public static double drivePID_P = 1.0;
	public static double drivePID_I = 0.0;
	public static double drivePID_D = 0.0;
	
	//Tote Height Integers
	public static  double toteHeight[] = {0.666667, 2.05, 3.058333, 4.066667, 5.075};
	public static int elevatorTarget = 0;
	public static boolean platformStatus = false;
	public static boolean underLipStatus = false;
	
	public static double gyroToDegrees = 0.0015;
	
	//Arm Positions
	public static double verticalArmPos; //to be decided
	public static double horizontalAForewardPos; //to be decided
	public static double horizontalABackPos; //to be decided
	public static double recyclingContainerReleasePos; //the position that the arm will be when the
													  //recycling container touches the autozone AND may be released (this position needs to be far enough so that the arm can be pulled away)

	//Autonomous Distances
	public static double pickup1stBinXDistance; //May be added if needed?
	public static double pickup1stBinYDistance; //to be decided
	public static double pickup2ndBinXDistance;
	public static double pickup2ndBinYDistance;
	public static double unhookBinXDistance = 1; //drive one foot foreward
	public static double unhookBinYDistance = 0.0;
	
	//Autonomous Drive Power Variables
	public static double pickupBinDrivePower;
	public static double driveBackToOrigPosPower;
	public static double unhookBinPower;
	

}