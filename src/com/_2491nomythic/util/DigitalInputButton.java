package com._2491nomythic.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

public class DigitalInputButton extends Button {
	DigitalInput m_input;
	
	public DigitalInputButton (DigitalInput input) {
		m_input = input;
	}
	
	public DigitalInputButton (int channel) {
		m_input = new DigitalInput(channel);
	}
	
	public boolean get() {
		return !m_input.get();
	}
	
}