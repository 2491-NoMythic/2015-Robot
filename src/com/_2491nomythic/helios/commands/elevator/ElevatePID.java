package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

public class ElevatePID extends CommandBase {
	
	private double target;
	
	public ElevatePID(double target) {
		requires(elevator);
		this.target = target;
	}

	protected void initialize() {
		elevator.setPID(target);
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return elevator.onTarget();
	}

	protected void end() {
		elevator.stop();
	}

	protected void interrupted() {
		end();
	}
	
}
