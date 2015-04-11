package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 * Sets the Arm to the specified position.
 */
public class RunArmWithPID extends CommandBase {
	double target;
	private Timer timer;
	private double startPos;
	private boolean differenceIsNegative;
	private boolean hasRunOnce;
	
	/**
	 * Sets the Arm to the specified position.
	 * @param targetPosition The position to set the arm to.
	 */
	public RunArmWithPID(double targetPosition) {
		// Use requires() here to declare subsystem dependencies
		requires(arm);
		target = targetPosition;
		timer = new Timer();
		hasRunOnce = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		arm.setPID(target);
		arm.enable();
		timer.start();
		timer.reset();
		startPos = arm.getPosition(); 
		differenceIsNegative = 0 > target - startPos;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(timer.get() > 0.2 && !hasRunOnce) {
			timer.stop();
			hasRunOnce = true;
			if(startPos == arm.getPosition() || (differenceIsNegative && arm.getRate() > 0) || (!differenceIsNegative && arm.getRate() < 0)) {
				this.cancel(); //arm shouldn't be at same position- cancel
							   //if our difference is negative, the motor input should also be negative
							   //if our difference is positive the motor input should also be positive
			}
			
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return arm.onTarget();
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
