package com._2491nomythic.helios.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Runs the camera. 'nuff said.
 */
public class RunCamera extends CommandBase {
	
	private Timer timer;
	private double nextRun;
	
	/**
	 * Runs the camera. 'nuff said.
	 */
	public RunCamera() {
		requires(camera);
		timer = new Timer();
		setRunWhenDisabled(true);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		camera.startImageAcquisition();
		timer.start();
		timer.reset();
		nextRun = timer.get();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > nextRun) {
			nextRun = nextRun + 0.05;
			camera.updateDriverstationImage();
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		camera.stopImageAcquisition();
		timer.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
