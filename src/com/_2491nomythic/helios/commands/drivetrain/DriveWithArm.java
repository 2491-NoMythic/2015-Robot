package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithArm extends CommandBase {
	
	public DriveWithArm() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double radiansPerSecond = Math.toRadians(arm.getRate());
		double armPos = Math.toRadians(arm.getPosition());
		double feetPerSecond = radiansPerSecond * Constants.armLength;
		double horizontalFeetPerSecond = feetPerSecond * Math.cos(armPos);
		drivetrain.drive(Variables.armCompensationMultiplier * horizontalFeetPerSecond, Variables.armCompensationMultiplier * horizontalFeetPerSecond, 0, 0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
