package com._2491nomythic.helios.settings;

public class HandheldControllerMap {

	public static final String controllerType = "HandheldController";

	//Axes
	//Left Joystick on Controller 0: Drive
	public static final int DriveController = 0;
	public static final int DriveAxisX = 1;
	public static final int DriveAxisY = 2;
	//Right X Axis on Controller 0: Turn
	public static final int TurnController = 0;
	public static final int TurnAxis = 3;
	//Right Y Axis on Controller 1: Elevator
	public static final int ElevatorController = 1;
	public static final int ElevatorAxis = 4;
	//Left Y Axis on Controller 1: DreamBelt Manual
	public static final int DreamBeltController = 1;
	public static final int DreamBeltAxis = 2;
	//Up-Down Dpad on Controller 1: DreamBelt Turn
	public static final int DreamBeltTurnController = 1;
	public static final int DreamBeltTurnAxis = 6;
	

	//Buttons
	//Controller 1: Lift Bin To Specific Heights
	public static final int ToteHeightController = 1;
	public static final int Tote456HeightToggleButton = 6;
	public static final int Tote1or4HeightButton = 1;
	public static final int Tote2or5HeightButton = 2;
	public static final int Tote3or6HeightButton = 4;
	
}
