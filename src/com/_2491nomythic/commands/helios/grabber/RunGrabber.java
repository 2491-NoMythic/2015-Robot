package com._2491nomythic.commands.helios.grabber;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

/**
 *
 */
public class RunGrabber extends CommandBase {
	
	
	public RunGrabber() {
		requires(grabber);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		grabber.set(oi.getAxisForDrive(ControllerMap.ArmController, ControllerMap.GrabberAxis));
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
