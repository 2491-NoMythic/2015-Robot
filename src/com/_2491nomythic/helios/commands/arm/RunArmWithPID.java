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
	
	/**
	 * Sets the Arm to the specified position.
	 * @param targetPosition The position to set the arm to.
	 */
	public RunArmWithPID(double targetPosition) {
		// Use requires() here to declare subsystem dependencies
		requires(arm);
		target = targetPosition;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		arm.setPID(target);
		arm.enable();
		timer.start();
		timer.reset();
		startPos = arm.getPosition(); 
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(timer.get() > 0.2) {
			timer.reset();
			timer.stop();
			if(startPos == arm.getPosition() || (arm.getRate() > 0 != arm.get() > 0)) {
				this.cancel(); //arm shouldn't be at same position- cancel
			}
			
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return arm.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		arm.disable();
		arm.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
