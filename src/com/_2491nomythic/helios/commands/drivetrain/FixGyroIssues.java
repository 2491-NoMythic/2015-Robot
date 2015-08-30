package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

/**
 * Reinitializes the gyro.
 */
public class FixGyroIssues extends CommandBase {
	
	/**
	 * Reinitializes the gyro.
	 */
	public FixGyroIssues() {
		requires(drivetrain);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		drivetrain.getGyro().recalibrate();
		drivetrain.getGyro().setSensitivity(Variables.gyroToDegrees);
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
		
		end();
	}
}
