package com._2491nomythic.util.components.interfaces;

public interface Encoder {
	double getPosition();
	double setDistancePerPulse(double distancePerPulse);
	void reset();
	void resetTo(double position);
}
