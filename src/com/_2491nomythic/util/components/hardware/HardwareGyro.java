package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.Gyroscope;

import edu.wpi.first.wpilibj.Gyro;

public class HardwareGyro implements Gyroscope {
	
	private final Gyro gyro;
	
	public HardwareGyro(int channel, double sensitivity) {
		this.gyro = new Gyro(channel);
		gyro.setSensitivity(sensitivity);
	}
	
	public HardwareGyro(int channel) {
		this.gyro = new Gyro(channel);
	}

	@Override
	public double getAngle() {
		return gyro.getAngle();
	}

	@Override
	public double getRate() {
		return gyro.getRate();
	}

	@Override
	public void reset() {
		gyro.reset();
	}

	@Override
	public void setSensitivity(double sensitivity) {
		gyro.setSensitivity(sensitivity);
	}

	@Override
	public void recalibrate() {
		gyro.initGyro();
	}
	
}
