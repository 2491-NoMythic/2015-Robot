package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Gyroscope;

public class MockGyroscope implements Gyroscope {
	private double angle;
	private double degreesPerSecond;
	
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
	
	public void setDegreesPerSecond(double degreesPerSecond) {
		this.degreesPerSecond = degreesPerSecond;
	}

	@Override
	public double degreesPerSecond() {
		return degreesPerSecond;
	}

	@Override
	public void setSensitivity(double sensitivity) { //This isn't a real Gyro. Sensitivity only matters for real Gyros
		
	}

	@Override
	public void recalibrate() { //This isn't a real Gyro. 
		
	}

	@Override
	public void reset() {
		this.angle = 0.0;
		
	}
	
}
