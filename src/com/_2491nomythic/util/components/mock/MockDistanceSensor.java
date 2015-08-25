package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.DistanceSensor;

public class MockDistanceSensor implements DistanceSensor {
	private double distanceTraveled;
	
	public MockDistanceSensor(double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}
	
	public MockDistanceSensor() {
		this.distanceTraveled = 0.0;
	}
	@Override
	public double getDistanceInFeet() {
		return distanceTraveled;
	}

}
