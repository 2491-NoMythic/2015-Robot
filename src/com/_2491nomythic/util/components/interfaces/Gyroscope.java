package com._2491nomythic.util.components.interfaces;

public interface Gyroscope {
	double getAngle();
	double getRate();
	void setSensitivity(double sensitivity);
	void recalibrate();
	void reset();
}
