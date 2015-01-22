package com._2491nomythic.helios.commands;

import edu.wpi.first.wpilibj.command.Command; 

import com._2491nomythic.helios.Robot;
import com._2491nomythic.helios.settings.HandheldControllerMap;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.driveCartesian(oi.getAxis(HandheldControllerMap.DriveController, HandheldControllerMap.DriveAxisX), oi.getAxis(HandheldControllerMap.DriveController, HandheldControllerMap.DriveAxisY), );
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
