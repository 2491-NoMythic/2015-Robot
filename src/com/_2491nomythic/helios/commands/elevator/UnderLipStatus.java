package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Changes the height of the elevator based on whether or not to be under the lip of a tote.
 */
public class UnderLipStatus extends Command {
	
	public static enum switchType {
		True, False, Toggle
	}
	
	private switchType type;
	private static GoToToteHeight go;
	
	/**
	 * Changes the height of the elevator based on whether or not to be under the lip of a tote.
	 * @param type Whether or not to be under the lip of a tote.
	 */
	public UnderLipStatus(switchType type) {
		this.type = type;
		if (go == null) {
			go = new GoToToteHeight();
		}
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if (type == switchType.True) {
			Variables.underLipStatus = true;
		}
		else if (type == switchType.False) {
			Variables.underLipStatus = false;
		}
		else {
			Variables.underLipStatus = !Variables.underLipStatus;
		}
		go.start();
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
