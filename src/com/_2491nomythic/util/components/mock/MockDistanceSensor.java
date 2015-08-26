package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.DistanceSensor;

public class MockDistanceSensor implements DistanceSensor {
	private double distanceInFeet;
	
	public MockDistanceSensor(double distanceInFeet) {
		this.distanceInFeet = distanceInFeet;
	}
	
	public MockDistanceSensor() {
		this.distanceInFeet = 0.0;
	}
	
	@Override
	public double getDistanceInFeet() {
		return distanceInFeet;
	}
	
	public void setDistanceInFeet(double distanceInFeet) {
		this.distanceInFeet = distanceInFeet;
	}
	
	public void setDistanceInInches(double distanceInInches) {
		setDistanceInFeet(distanceInInches / 12);
	}

}
