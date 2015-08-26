package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.LimitSwitch;

import edu.wpi.first.wpilibj.DigitalInput;

public class HardwareLimitSwitch implements LimitSwitch {
	
	private final DigitalInput limitSwitch;
	private boolean isInverted;
	
	public HardwareLimitSwitch(int channel) {
		this.limitSwitch = new DigitalInput(channel);
		this.isInverted = false;
	}
	
	public HardwareLimitSwitch(int channel, boolean inverted) {
		this.limitSwitch = new DigitalInput(channel);
		this.isInverted = inverted;
	}

	@Override
	public boolean isTriggered() {
		return isInverted ^ limitSwitch.get();
	}
	
}
