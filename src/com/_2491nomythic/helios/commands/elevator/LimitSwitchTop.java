package com._2491nomythic.helios.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 */
public class LimitSwitchTop extends Command {
	
	DigitalInput LimitSwitch;

    public LimitSwitchTop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	LimitSwitch = new DigitalInput(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(LimitSwitch.get()) {
    		motorElevator.set(0)
    	}
    		
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
