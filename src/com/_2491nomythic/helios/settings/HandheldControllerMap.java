package com._2491nomythic.helios.settings;

public class HandheldControllerMap {
	
	public static final String controllerType = "HandheldController";
	
	// Axes
	// Left Joystick on Controller 0: Drive
	public static final int DriveController = 0;
	public static final int DriveAxisX = 0;
	public static final int DriveAxisY = 1;
	// Right X Axis on Controller 0: Turn
	public static final int TurnController = 0;
	public static final int TurnAxis = 2;
	// Right Y Axis on Controller 1: Elevator
//	public static final int CodriverElevatorController = 1;
//	public static final int CodriverElevatorAxis = 3;
	// Left Y Axis on Controller 1: Arm Manual
	public static final int ArmController = 1;
	public static final int ArmAxis = 1;
	public static final int GrabberAxis = 3;
	
	
	// Buttons
	// Controller 1: Encoder Zero Button
	public static final int FasterArmButtonA = 6;
	public static final int FasterArmButtonB = 5;
	
	// Controller 1: Move To A Target Position Button
	public static final int setToTargetController = 1;
	public static final int setToTargetButton = 8;
	// Moving the elevator
	public static final int moveUpOneToteController = 1;
	public static final int moveUpOneToteButton = 2;
	public static final int moveDownOneToteController = 1;
	public static final int moveDownOneToteButton = 3;
	public static final int scoringPlatformStatusController = 1;
	public static final int scoringPlatformStatusButton = 1;
	public static final int getNextToteController = 0;
	public static final int getNextToteButton = 10;
	public static final int driveToToteButton = 9;
	public static final int driverElevatorController = 0;
	public static final int driverElevatorUp = 4;
	public static final int driverElevatorDown = 1;
	public static final int codriverElevatorController = 1;
	public static final int codriverElevatorUp = 4;
	public static final int codriverElevatorDown = 1;
	//Drive
	public static final int FasterDriveButtonA = 6;
	public static final int FasterDriveButtonB = 5;
}
