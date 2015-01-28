package com._2491nomythic.helios.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateDriverstation extends CommandBase {

	private Timer timer;
	private double nextRun;
	
	public UpdateDriverstation() {
		requires(extraSensors);
		timer = new Timer();
	}
	
	protected void initialize() {
		timer.start();
		timer.reset();
		nextRun = timer.get();
	}

	protected void execute() {
		if (timer.get() > nextRun) {
			nextRun = nextRun + 0.25;
			SmartDashboard.putNumber("Power Usage (watts)", extraSensors.getPowerDistributionPanel().getTotalPower());
			SmartDashboard.putNumber("Power Usage (amps)", extraSensors.getPowerDistributionPanel().getTotalCurrent());
			SmartDashboard.putNumber("Power Used (joules)", extraSensors.getPowerDistributionPanel().getTotalEnergy());
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		timer.stop();
	}

	protected void interrupted() {
		end();
	}
	
}