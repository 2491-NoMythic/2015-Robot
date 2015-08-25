package com._2491nomythic.util.components.interfaces;

public interface DistanceSensor {
	double getDistanceInFeet();
	default double getDistanceInInches() {
		return getDistanceInFeet() * 12;
	}
}
