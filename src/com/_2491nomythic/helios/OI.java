package com._2491nomythic.helios;


import edu.wpi.first.wpilibj.Joystick;  
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.helios.commands.arm.RunWithPID;
//Command imports
import com._2491nomythic.helios.commands.arm.ZeroEncoder;
import com._2491nomythic.helios.commands.elevator.DecrementToteHeight;
import com._2491nomythic.helios.commands.elevator.IncrementToteHeight;
import com._2491nomythic.helios.commands.elevator.PlatformStatus;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
	private final Joystick[] controllers = new Joystick[2];
	Button zeroArmEncoder, moveArmToPoint, moveUpOneTote, moveDownOneTote, scoringPlatformStatus;
	public int buttonIncrementer = 0;
	int hypotheticalMoveArmValue; //not sure what value Evan would like.... Will replace when known.
	
	public void init() {
		controllers[0] = new Joystick(Constants.ControllerOnePort);
		controllers[1] = new Joystick(Constants.ControllerTwoPort);
		
		moveArmToPoint = new JoystickButton(controllers[ControllerMap.ElevatorController], ControllerMap.SetToTargetButton);
		moveArmToPoint.whenPressed(new RunWithPID(hypotheticalMoveArmValue));
		
		zeroArmEncoder = new JoystickButton(controllers[ControllerMap.ElevatorController], ControllerMap.ZeroArmEncoderButton);
		zeroArmEncoder.whenPressed(new ZeroEncoder());
		
		moveUpOneTote = new JoystickButton(controllers[ControllerMap.moveUpOneToteController], ControllerMap.moveUpOneToteButton);
		moveUpOneTote.whenPressed(new IncrementToteHeight());
		
		moveDownOneTote = new JoystickButton(controllers[ControllerMap.moveDownOneToteController], ControllerMap.moveDownOneToteButton);
		moveDownOneTote.whenPressed(new DecrementToteHeight());
		
		scoringPlatformStatus = new JoystickButton(controllers[ControllerMap.scoringPlatformStatusController], ControllerMap.scoringPlatformStatusButton);
		scoringPlatformStatus.whenPressed(new PlatformStatus());
	}
	
	/**
	 * Get a controller
	 * @param id the ID of the controller.  0 = left or driver, 1 = right or codriver.
	 * @return the instance of the controller requested
	 */
	public Joystick getController(int id) {
		return controllers[id];
	}
	
	/**
	 * Get a button from a controller
	 * @param joystickID The id of the controller.  0 = left or driver, 1 = right or codriver.
	 * @param axisID The id of the button (for use in getRawButton)
	 * @return the result from running getRawButton(button)
	 */
	public boolean getButton(int joystickID, int buttonID) {
		return controllers[joystickID].getRawButton(buttonID);
	}
	
	/**
	 * Get an axis from a controller
	 * @param joystickID The id of the controller.  0 = left or driver, 1 = right or codriver.
	 * @param axisID The id of the axis (for use in getRawAxis)
	 * @return the result from running getRawAxis(axis)
	 */
	public double getAxis(int joystickID, int axisID) {
		return controllers[joystickID].getRawAxis(axisID);
	}
	
	/**
	 * Get an axis from a controller that is automatically squared and deadzoned
	 * @param joystickID The id of the controller.  0 = left or driver, 1 = right or driver
	 * @param axisID The id of the axis (for use in getRawAxis)
	 * @return the squared, deadzoned result from running getRawAxis
	 */
	public double getAxisForDrive(int joystickID, int axisID) {
		double result = controllers[joystickID].getRawAxis(axisID);
		result = result * Math.abs(result);
		return Math.abs(result) > 0.05 ? result : 0;
	}

	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
	
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

