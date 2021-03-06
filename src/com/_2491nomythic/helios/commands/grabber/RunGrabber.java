package com._2491nomythic.helios.commands.grabber;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

/**
 * Runs the grabber according to driver control in Tele-Op.
 */
public class RunGrabber extends CommandBase {
	
	private boolean hasBeenStopped = true;
	private int reverse = 1;
	
	/**
	 * Runs the grabber according to driver control in Tele-Op.
	 */
	public RunGrabber() {
		requires(grabber);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = oi.getAxisForDrive(ControllerMap.ArmController, ControllerMap.GrabberAxis);
		if (speed != 0) {
			if (hasBeenStopped) {
				if (arm.getPosition() > 10) {
					reverse = -1;
				}
				else if (arm.getPosition() < -10) {
					reverse = 1;
				}
			}
			
			if (!oi.getButton(ControllerMap.ArmController, ControllerMap.FasterGrabberButtonA) && !oi.getButton(ControllerMap.ArmController, ControllerMap.FasterGrabberButtonB)) {
				speed *= 0.5;
			}
			
			grabber.set(speed *  reverse);
			hasBeenStopped = false;
		}
		else {
			grabber.set(0.0);
			hasBeenStopped = true;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		grabber.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
