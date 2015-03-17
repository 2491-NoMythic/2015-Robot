package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Resets the arm encoder.
 */
public class ResetArmEncoder extends CommandBase {
	int state = 0;
	Timer timer = new Timer();
	double currentTime;
	
	public ResetArmEncoder() {
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
		
		switch (state) {
			case 0:
				if (arm.onTarget()) {
					state = 1;
				}
			case 1:
				arm.set(-0.25);
				currentTime = timer.get();
				state = 2;
			case 2:
				if (timer.get() >= currentTime + 0.2) {
					state = 3;
				}
			default:
				// Do nothing
		}
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return ((state == 3) && arm.getRate() == 0);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		state = 0;
		arm.resetEncoder();
		arm.set(0.0);
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		state = 0;
		arm.set(0.0);
	}
}
