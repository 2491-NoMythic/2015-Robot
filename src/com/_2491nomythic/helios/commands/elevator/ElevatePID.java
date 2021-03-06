package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class ElevatePID extends CommandBase {
	
	private double target;
	private Timer timer;
	private double startPos;
	
	public ElevatePID(double target) {
		requires(elevator);
		this.target = target;
	}

	protected void initialize() {
		elevator.setPID(target);
		timer.start();
		timer.reset();
		startPos = elevator.getPosition();
	}

	protected void execute() {
		if(timer.get() > 0.2) {
			timer.reset();
			timer.stop();
			if(startPos == elevator.getPosition() || elevator.getRate() > 0 != elevator.get() > 0) {
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
