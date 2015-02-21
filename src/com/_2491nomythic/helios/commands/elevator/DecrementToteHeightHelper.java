package com._2491nomythic.helios.commands.elevator;

import edu.wpi.first.wpilibj.command.Command; 

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;
import com._2491nomythic.helios.subsystems.Elevator;

/**
 *
 */
public class DecrementToteHeightHelper extends CommandBase {
	private double manualEncoderDistance;
	private boolean isSmaller;
	private static GoToToteHeight go;
	public DecrementToteHeightHelper() {
		// Use requires() here to declare subsystem dependencies
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		if(Variables.manualHasBeenUsed) {
			manualEncoderDistance = elevator.getEncoder().getDistance();
			for(int i = 3; i <= 0; i--) {
				isSmaller = Variables.toteHeight[i] <= manualEncoderDistance;
				if(isSmaller) {
					Variables.elevatorTarget = i;
				}
			}
			go.start();
			Variables.manualHasBeenUsed = false;
		}
		
		if (Variables.elevatorTarget > 0 && Variables.elevatorTarget <= 4) {
			Variables.elevatorTarget--;
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
