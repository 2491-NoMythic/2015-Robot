package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;


/**
 *
 */
public class IncrementToteHeightHelper extends CommandBase {
	private double manualEncoderDistance;
	private boolean isBigger;
	private static GoToToteHeight go;
	public IncrementToteHeightHelper() {
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if(Variables.manualHasBeenUsed) {
			manualEncoderDistance = elevator.getEncoder().getDistance();
			for(int i = 0; i <= 3; i++) {
				isBigger = Variables.toteHeight[i] >= manualEncoderDistance;
				if(isBigger) {
					Variables.elevatorTarget = i;
				}
			}
			go.start();
		}
		
		if (Variables.elevatorTarget >= 0 && Variables.elevatorTarget < 4) {
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
