package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Encoder;

public class MockEncoder implements Encoder {
	
	private double position;
	private double rate;
	
	public MockEncoder(double position, double rate) {
		this.position = position;
		this.rate = rate;
	}
	
	public MockEncoder(double position) {
		this.position = position;
		this.rate = 0.0;
	}
	
	public MockEncoder() {
		this.position = 0.0;
		this.rate = 0.0;
	}
	
	@Override
	public double getPosition() {
		return position;
	}
	
	public void setPosition(double position) {
		this.position = position;
	}

	@Override
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	@Override
	public void setDistancePerPulse(double distancePerPulse) { //Not the robot. Does nothing.
		
	}

	@Override
	public void reset() {
		position = 0.0;
	}

	@Override
	public void resetTo(double newPosition) {
		this.position = newPosition;
	}

}
