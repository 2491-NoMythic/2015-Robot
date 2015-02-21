package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManuallyResetArmEncoder extends CommandBase {

    public ManuallyResetArmEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.resetEncoder();
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
