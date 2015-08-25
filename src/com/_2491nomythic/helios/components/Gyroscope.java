package com._2491nomythic.helios.components;

public interface Gyroscope {
	double getAngle();
	double degreesPerSecond(); //Likely not going to be used
	void reset();
}
