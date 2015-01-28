package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IncrementToteHeightCounter extends CommandBase {

    public IncrementToteHeightCounter() {
        // Use requires() here to declare subsystem dependencies
    	//Just reading from OI, no need to require anything
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(oi.buttonIncrementer > 0 && oi.buttonIncrementer < 7) {
    	oi.buttonIncrementer++;
    }
    	else if(oi.buttonIncrementer < 0) {
    		oi.buttonIncrementer = 0;
    	}
    	else if(oi.buttonIncrementer > 6) {
    		oi.buttonIncrementer = 6;
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
