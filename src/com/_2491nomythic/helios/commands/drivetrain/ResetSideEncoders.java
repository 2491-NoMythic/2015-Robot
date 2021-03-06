package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;

/**
 * Resets the side encoders on the drivetrain.
 */
public class ResetSideEncoders extends CommandBase {
	
	/**
	 * Resets the side encoders on the drivetrain.
	 */
	public ResetSideEncoders() {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
		setRunWhenDisabled(true);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.getLeftEncoder().reset();
		drivetrain.getRightEncoder().reset();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
