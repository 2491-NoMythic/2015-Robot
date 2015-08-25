package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Motor;

public class MockMotor implements Motor {
	private double speed;
	
	public MockMotor(double speed) {
		this.speed = speed;
	};

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public double getSpeed() {
		return speed;
	}
	
}
