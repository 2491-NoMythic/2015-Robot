package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PlatformStatus extends Command {

    public PlatformStatus() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if(Variables.PlatformStatus = true) {
    		Variables.PlatformStatus = false;
    	}
    	else {
    		Variables.PlatformStatus = true;
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
