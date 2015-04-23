package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class RunArmPosition extends CommandBase {
	
	private double target;
	private double power;
	private double start;
	private Timer timer;
	
	public RunArmPosition(double power, double target) {
		this.power = Math.abs(power);
		this.target = target;
		timer = new Timer();
	}
	
	protected void initialize() {
		arm.setMaxAccleration(2.0);
		start = arm.getPosition();
		if (target > start) {
			arm.set(power);
		}
		else {
			arm.set(-1 * power);
		}
	}
	
	protected void execute() {
		if (timer.get() > 0.2) {
			if (arm.getPosition() == target || arm.getPosition() - start > 0 != arm.get() > 0) {
				arm.stop();
				System.out.println("Something is wrong with the arm encoder!");
			}
		}
		if (target > start) {
			if (arm.getPosition() > target) {
				arm.stop();
			}
		}
		else {
			if (arm.getPosition() < target) {
				arm.stop();
			}
		}
	}
	
	protected boolean isFinished() {
		return arm.get() == 0;
	}
	
	protected void end() {
		arm.stop();
		arm.setMaxAccleration(0.05);
	}
	
	protected void interrupted() {
		end();
	}
	
}
