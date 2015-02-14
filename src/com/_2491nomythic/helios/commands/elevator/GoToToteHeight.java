package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

/**
 *
 */
public class GoToToteHeight extends CommandBase {
	
    public GoToToteHeight() {
        // Use requires() here to declare subsystem dependencies
        requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double heightToSet = Variables.toteHeight[Variables.elevatorTarget];
    	
		if(Variables.platformStatus) {
    		heightToSet += 0.166667;
    	}
		if(Variables.underLipStatus) {
			heightToSet -= Variables.underLipDistance; 
		}
    		
    	elevator.setPID(heightToSet);
    	
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
