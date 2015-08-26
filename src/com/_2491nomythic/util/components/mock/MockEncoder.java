package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Encoder;

public class MockEncoder implements Encoder {
	private double position;
	
	public MockEncoder(double position) {
		this.position = position;
	}
	
	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public double setDistancePerPulse(double distancePerPulse) { //Not the robot. Does nothing.
		return 0;
	}

	@Override
	public void reset() {
		position = 0.0;
	}

	@Override
	public void resetTo(double newPosition) {
		reset();
	}

}
