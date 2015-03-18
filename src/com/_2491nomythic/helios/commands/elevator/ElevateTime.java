package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 * Sets the power of the elevator motor for a specified time.
 */
public class ElevateTime extends CommandBase {
	double powerInput;
	double time;
	Timer timer;
	
	/**
	 * 
	 * @param power The power to set the elevator motor to.
	 * @param time The amount of time to run the elevator for.
	 */
	public ElevateTime(double power, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(elevator);
		powerInput = power;
		this.time = time;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
		elevator.set(powerInput);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > Math.abs(time)) {
			elevator.set(0.0);
		}
		else {
			elevator.set(powerInput);
		}
	}
	
	// Mak this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.get() > Math.abs(time));
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		elevator.set(0.0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
