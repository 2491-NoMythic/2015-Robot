package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;


/**
 *
 */
public class PlatformStatus extends CommandBase {
	public static enum switchType {
		True, False, Toggle
	}
	
	private switchType type;
	
	public PlatformStatus(switchType type) {
		this.type = type;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if (type == switchType.True) {
			Variables.platformStatus = true;
		}
		else if (type == switchType.False) {
			Variables.platformStatus = false;
		}
		else {
			Variables.platformStatus = !Variables.platformStatus;
		}
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
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
