package com._2491nomythic.util.components.interfaces;

public interface Encoder {
	double getPosition();
	double getRate();
	void setDistancePerPulse(double distancePerPulse);
	void reset();
	void resetTo(double newPosition);
}

