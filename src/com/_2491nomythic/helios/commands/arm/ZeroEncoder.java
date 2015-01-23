package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroEncoder extends CommandBase {
	int state = 0;
	Timer timer = new Timer();
	double timeHolder;
    public ZeroEncoder() {
        // Use requires() here to declare subsystem dependencies
        requires(arm);
        timer.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.setPID(15);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(arm.onTarget()) {
    		state = 1;
    	}
    	
    	if(state == 1) {
    		arm.set(-0.25);
    		state++;
    		timeHolder = timer.get();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((state == 1) && arm.returnPIDRate() != 0) {
    		return false;
    	}
    	if((state == 1) && arm.returnPIDRate() == 0) {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	state = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
