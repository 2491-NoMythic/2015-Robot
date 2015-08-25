package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.DistanceSensor;

import edu.wpi.first.wpilibj.AnalogInput;

public class HardwareAnalogUltrasonic implements DistanceSensor {
	
	private final AnalogInput sonar;
	private static final double VoltsToFeet = 1000.0 / 9.8 / 12;
	
	public HardwareAnalogUltrasonic(int channel) {
		this.sonar = new AnalogInput(channel);
	}

	@Override
	public double getDistanceInFeet() {
		return sonar.getVoltage() * VoltsToFeet;
	}
	
}
