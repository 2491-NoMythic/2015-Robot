package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

/**
 *
 */
public class ElevatePower extends CommandBase {
	double power;
	
	public ElevatePower(double power) {
		this.power = power;
		requires(elevator);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		elevator.set(power);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		elevator.set(power);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		elevator.set(0.0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
