package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;

/**
 *
 */
public class FixGyroIssues extends CommandBase {
	
	private boolean done = false;

    public FixGyroIssues() {
    	requires(drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	drivetrain.getGyro().initGyro();
    	done = true;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    }
}
