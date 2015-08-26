package com._2491nomythic.util.components.interfaces;

public interface Encoder {
	double getPosition();
	double setDistancePerPulse();
	void reset();
	void resetTo(double degrees);
}
