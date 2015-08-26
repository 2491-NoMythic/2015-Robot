package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Gyroscope;

public class MockGyroscope implements Gyroscope {
	private double angle;
	private double rate;
	
	public MockGyroscope(double angle) {
		this.angle = angle;
	}
	
	public MockGyroscope() {
		this.angle = 0.0;
	}
	
	@Override
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public void setSensitivity(double sensitivity) {
		// This isn't a real Gyro. Sensitivity only matters for real Gyros
	}

	@Override
	public void recalibrate() { 
		reset(); // This isn't a real Gyro, so this is all that's needed.
	}

	@Override
	public void reset() {
		this.angle = 0.0;
	}
	
}
