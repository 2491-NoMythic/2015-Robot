package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;


/**
 * A command that is used in conjunction with GoToToteHeight to move the elevator up by the height of one tote.
 */
public class IncrementToteHeightHelper extends CommandBase {
	private double manualEncoderDistance;
	private boolean isBigger;
	
	/**
	 * A command that is used in conjunction with GoToToteHeight to move the elevator up by the height of one tote.
	 */
	public IncrementToteHeightHelper() {
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if(Variables.manualHasBeenUsed) {
			manualEncoderDistance = elevator.getEncoder().getPosition();
			for(int i = 0; i < Variables.toteHeight.length; i++) {
				isBigger = Variables.toteHeight[i] >= manualEncoderDistance;
				if(isBigger) {
					Variables.elevatorTarget = i;
				}
			}
			Variables.manualHasBeenUsed = false;
		}
		
		if (Variables.elevatorTarget >= 0 && Variables.elevatorTarget < 3) {
			Variables.elevatorTarget++;
		}
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
