package com._2491nomythic.helios.settings;

public class Variables {
	public static double armPID_P = 1.0;
	public static double armPID_I = 0.0;
	public static double armPID_D = 0.0;
	public static double elevatorPID_P = 1.0;
	public static double elevatorPID_I = 0.0;
	public static double elevatorPID_D = 0.0;
	
	//Tote Height Integers
	public static  double toteHeight[] = {0.666667, 2.05, 3.058333, 4.066667, 5.075};
	public static int elevatorTarget = 0;
	public static boolean PlatformStatus = false;
	
	public static double gyroToDegrees = 0.0015;
	
	//Arm Positions
	public static double verticalArmPos; //to be decided
	public static double horizontalAForewardPos; //to be decided
	public static double horizontalABackPos; //to be decided
	
	//Autonomous Distances
	public static double pickup1stBinYDistance; //to be decided
	public static double pickup1stBinXDistance = 0.0; //can and may be changed
	
	//Autonomous Drive Power Variables
	public static double pickup1stBinPower;
	
}