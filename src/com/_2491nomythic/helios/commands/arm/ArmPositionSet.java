package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

/**
 *
 */
public class ArmPositionSet extends CommandBase {
	
	private double armStickPos;
	private boolean hasBeenStopped;
    public ArmPositionSet() {
    	requires(arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hasBeenStopped = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	armStickPos = oi.getAxis(ControllerMap.ArmController, ControllerMap.ArmAxis);
    	if(Math.abs(0.05) >= armStickPos && !(hasBeenStopped)) {
    		arm.stop();
    		hasBeenStopped = true;
    	}
    	else if(armStickPos >= 0.05) {
    	arm.set(armStickPos);
    	hasBeenStopped = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}