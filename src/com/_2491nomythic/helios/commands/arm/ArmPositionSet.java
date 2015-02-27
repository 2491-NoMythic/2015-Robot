package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

/**
 *
 */
public class ArmPositionSet extends CommandBase {
	
	private double armStickPos;
	private boolean hasBeenStopped;
	private int reverse = 1;
	
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
		armStickPos = oi.getAxisForDrive(ControllerMap.ArmController, ControllerMap.ArmAxis);
		double multiplier = 0.25;
		if (oi.getButton(ControllerMap.ArmController, ControllerMap.FasterArmButtonA)) {
			multiplier *= 2;
		}
		if (oi.getButton(ControllerMap.ArmController, ControllerMap.FasterArmButtonB)) {
			multiplier *= 2;
		}
		if (Math.abs(armStickPos) >= 0.05) {
			if (hasBeenStopped) {
				if (arm.getPosition() > 0) {
					reverse = -1;
				}
				else {
					reverse = 1;
				}
			}
			arm.set(-1 * armStickPos * multiplier * reverse);
			hasBeenStopped = false;
		}
		else if (!(hasBeenStopped)) {
			arm.stop();
			hasBeenStopped = true;
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
