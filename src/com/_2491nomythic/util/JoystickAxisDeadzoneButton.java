package com._2491nomythic.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickAxisDeadzoneButton extends Button {
	GenericHID joystick;
	int axisNumber;
	double deadzoneSize;
	boolean activeInDeadzone;
	
	
	/**
	 * Trigger commands based on if a joystick is in its deadzone.
	 * @param joystick The joystick to check
	 * @param axisNumber The axis to check on the joystick
	 * @param deadzoneSize The size of the deadzone, 1.0 is the whole joystick, 0.0 is nothing
	 * @param activeInDeadzone If true, button will be active while in the deadzone, otherwise, button will be active while outside of the deadzone
	 */
	public JoystickAxisDeadzoneButton(GenericHID joystick, int axisNumber, double deadzoneSize, boolean activeInDeadzone) {
		this.joystick = joystick;
		this.axisNumber = axisNumber;
		this.deadzoneSize = Math.abs(deadzoneSize);
		this.activeInDeadzone = activeInDeadzone;
	}
	
	@Override
	public boolean get() {
		return activeInDeadzone != Math.abs(joystick.getRawAxis(axisNumber)) > deadzoneSize;
	}
	
}
