package com._2491nomythic.util.components.mock;

import com._2491nomythic.util.components.interfaces.Solenoid;

public class MockSolenoid implements Solenoid {
	private boolean extended;
	
	public MockSolenoid(boolean extended) {
		this.extended = extended;
	}
	
	@Override
	public boolean solenoidExtended() {
		// TODO Auto-generated method stub
		return extended;
	}

	@Override
	public void extend() {
		// TODO Auto-generated method stub
		this.extended = true;
	}

	@Override
	public void retract() {
		// TODO Auto-generated method stub
		this.extended = false;
	}

}
