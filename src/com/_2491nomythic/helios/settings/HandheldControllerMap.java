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
	//Left Y Axis on Controller 1: DreamBelt
	public static final int DreamBeltController = 1;
	public static final int DreamBeltAxis = 2;
	//Up & Down D-Pad on Controller 1: DreamBelt Turn
	public static final int DreamBeltTurnController = 1;
	public static final int DreamBeltTurnAxis = 6;

	//Buttons
	//LB Button on Controller 1: DreamBelt Grab
	public static final int DreamBeltGrabController = 1;
	public static final int DreamBeltGrabButton = 5;
	
}
