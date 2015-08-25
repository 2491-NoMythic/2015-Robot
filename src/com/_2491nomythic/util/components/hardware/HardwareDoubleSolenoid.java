package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.Solenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HardwareDoubleSolenoid implements Solenoid {
	
	private final DoubleSolenoid solenoid;
	
	public HardwareDoubleSolenoid(int extendChannel, int retractChannel) {
		this.solenoid = new DoubleSolenoid(extendChannel, retractChannel);
	}

	@Override
	public boolean solenoidExtended() {
		return solenoid.get() == Value.kForward ? true : false;
	}

	@Override
	public void extend() {
		solenoid.set(Value.kForward);
	}

	@Override
	public void retract() {
		solenoid.set(Value.kReverse);
	}
	
}
