package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

/**
 *
 */
public class GoToToteHeight extends CommandBase {
	int toteHeightInput;
    public GoToToteHeight(int heightInput) {
        // Use requires() here to declare subsystem dependencies
        requires(elevator);
        toteHeightInput = heightInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.setPID(Variables.toteHeight[toteHeightInput]);
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
