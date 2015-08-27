package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.Encoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class HardwareEncoder implements Encoder {
	
	private final edu.wpi.first.wpilibj.Encoder encoder;
	private double offset = 0;
	
	public HardwareEncoder(int channelA, int channelB, boolean inverted, EncodingType encodingType, double distancePerPulse) {
		encoder = new edu.wpi.first.wpilibj.Encoder(channelA, channelB, inverted, encodingType);
		encoder.setDistancePerPulse(distancePerPulse);
		encoder.reset();
	}
	
	public HardwareEncoder(int channelA, int channelB, boolean inverted, double distancePerPulse) {
		encoder = new edu.wpi.first.wpilibj.Encoder(channelA, channelB, inverted);
		encoder.setDistancePerPulse(distancePerPulse);
		encoder.reset();
	}
	
	public HardwareEncoder(int channelA, int channelB, double distancePerPulse) {
		encoder = new edu.wpi.first.wpilibj.Encoder(channelA, channelB);
		encoder.setDistancePerPulse(distancePerPulse);
		encoder.reset();
	}
	
	public HardwareEncoder(int channelA, int channelB) {
		encoder = new edu.wpi.first.wpilibj.Encoder(channelA, channelB);
		encoder.reset();
	}

	@Override
	public double getPosition() {
		return encoder.getDistance() + offset;
	}
	
	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public void setDistancePerPulse(double distancePerPulse) {
		encoder.setDistancePerPulse(distancePerPulse);
	}

	@Override
	public void reset() {
		encoder.reset();
	}

	@Override
	public void resetTo(double newPosition) {
		offset = newPosition - encoder.getDistance();
	}
	
}
