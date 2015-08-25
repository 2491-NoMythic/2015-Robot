package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.Motor;

import edu.wpi.first.wpilibj.SpeedController;

public class HardwareMotor implements Motor {
	
	private final SpeedController controller;
	
	public HardwareMotor(SpeedController controller) {
		this.controller = controller;
	}

	@Override
	public void setSpeed(double speed) {
		controller.set(speed);
	}

	@Override
	public double getSpeed() {
		return controller.get();
	}
	
}
