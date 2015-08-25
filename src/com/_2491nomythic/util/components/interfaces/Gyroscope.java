package com._2491nomythic.util.components.interfaces;

public interface Gyroscope {
	double getAngle();
	double degreesPerSecond(); //Likely not going to be used
	void setSensitivity(double sensitivity);
	void recalibrate();
	void reset();
}
