package com._2491nomythic.helios.commands.grabber;

import com._2491nomythic.helios.commands.CommandBase;

/**
 *
 */
public class RotateGrabber extends CommandBase {
	
	private double speed;
	
	public RotateGrabber(double speed) {
		requires(grabber);
		this.speed = speed;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		grabber.set(speed);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
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
