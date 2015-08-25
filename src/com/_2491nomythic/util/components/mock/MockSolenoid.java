package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Solenoid;

public class MockSolenoid implements Solenoid {
	private boolean extended;
	
	public MockSolenoid(boolean extended) {
		this.extended = extended;
	}
	
	@Override
	public boolean solenoidExtended() {
		return extended;
	}

	@Override
	public void extend() {
		this.extended = true;
	}

	@Override
	public void retract() {
		this.extended = false;
	}

}
