package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class ElevatePID extends CommandBase {
	
	private double target;
	private Timer timer;
	private double startPos;
	private boolean hasRunOnce;
	
	public ElevatePID(double target) {
		requires(elevator);
		this.target = target;
	}

	protected void initialize() {
		elevator.setPID(target);
		timer.start();
		timer.reset();
		startPos = elevator.getPosition();
		hasRunOnce = false;
	}

	protected void execute() {
		if(timer.get() > 0.2 && !hasRunOnce) {
			timer.stop();
			hasRunOnce = true;
			if(startPos == elevator.getPosition()) {
				this.cancel();
			}
		}
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
