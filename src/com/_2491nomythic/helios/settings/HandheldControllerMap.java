package com._2491nomythic.helios.settings;

public class HandheldControllerMap {

	public static final String controllerType = "HandheldController";
	
	//Axes
	//Left Joystick on Controller 0: Drive
	public static final int DriveController = 0;
	public static final int DriveAxisX = 0;
	public static final int DriveAxisY = 1;
	//Right X Axis on Controller 0: Turn
	public static final int TurnController = 0;
	public static final int TurnAxis = 2;
	//Right Y Axis on Controller 1: Elevator
	public static final int ElevatorController = 1;
	public static final int ElevatorAxis = 3;
	//Left Y Axis on Controller 1: DreamBelt Manual
	public static final int ArmController = 1;
	public static final int ArmAxis = 1;
	//Up-Down Dpad on Controller 1: DreamBelt Turn
	public static final int ArmTurnController = 1;
	public static final int ArmTurnAxis = 5;
	

	//Buttons
	//Controller 1: Encoder Zero Button
	public static final int ZeroArmEncoderButton = 3;
	//Controller 1: Move To A Target Position Button
	public static final int SetToTargetButton = 8;
	
}
