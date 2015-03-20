package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

/**
 * Keeps the arm from falling during Tele-Op.
 */
public class KeepArmFromFalling extends CommandBase {
	
	private double target;
	private double tolerance;
	
	/**
	 * Holds the arm in place
	 * WARNING!!! This command doesn't requires(arm), so it needs to be canceled manually so it doesn't constantly set the arm to 0;
	 * @param tolerance
	 */
	public KeepArmFromFalling(double tolerance) {
		//requires(arm);
		this.tolerance = tolerance;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		target = arm.getPosition();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (arm.getPosition() > 0) {
			if (arm.get() == 0) {
				if (arm.getPosition() > target + tolerance) {
					arm.set((target - arm.getPosition()) / 10);
				}
			}
			else {
				if (arm.getPosition() < target) {
					arm.stop();
				}
				else {
					arm.set((target - arm.getPosition()) / 10);
				}
			}
		}
		else {
			if (arm.get() == 0) {
				if (arm.getPosition() < target - tolerance) {
					arm.set((target - arm.getPosition()) / 10);
				}
			}
			else {
				if (arm.getPosition() > target) {
					arm.stop();
				}
				else {
					arm.set((target - arm.getPosition()) / 10);
				}
			}
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
