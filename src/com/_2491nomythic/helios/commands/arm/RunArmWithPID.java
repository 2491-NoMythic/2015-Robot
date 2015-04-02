package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;


/**
 * Sets the Arm to the specified position.
 */
public class RunArmWithPID extends CommandBase {
	double target;
	
	/**
	 * Sets the Arm to the specified position.
	 * @param targetPosition The position to set the arm to.
	 */
	public RunArmWithPID(double targetPosition) {
		// Use requires() here to declare subsystem dependencies
		requires(arm);
		target = targetPosition;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		arm.setPID(target);
		arm.enable();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return arm.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		arm.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
