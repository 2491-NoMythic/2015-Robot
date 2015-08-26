package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.LimitSwitch;

public class MockLimitSwitch implements LimitSwitch {
	private boolean isTriggered;
	
	public MockLimitSwitch(boolean isTriggered) {
		this.isTriggered = isTriggered;
	}
	
	@Override
	public boolean isTriggered() {
		return isTriggered;
	}
	
	public void setTriggered(boolean triggered) {
		this.isTriggered = triggered;
	}
	
}
