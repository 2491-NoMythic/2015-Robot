package com._2491nomythic.helios.commands.grabber;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 * Runs the grabber with a specified power for a specified time.
 */
public class RunGrabberTime extends CommandBase {
	double powerInput;
	double time;
	Timer timer;
	
	/**
	 * 
	 * @param power The power to apply to the grabber motor.
	 * @param time The amount of time to run the grabber for.
	 */
	public RunGrabberTime(double power, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(grabber);
		powerInput = power;
		this.time = time;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
		grabber.set(powerInput);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > Math.abs(time)) {
			grabber.set(0.0);
		}
		else {
			grabber.set(powerInput);
		}
	}
	
	// Mak this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > Math.abs(time));
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		grabber.set(0.0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
