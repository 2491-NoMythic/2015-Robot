package com._2491nomythic.helios.commands;

/**
 *
 */
public class RunCamera extends CommandBase {
	
	public RunCamera() {
		requires(camera);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		camera.startImageAcquisition();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		camera.updateDriverstationImage();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		camera.stopImageAcquisition();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
