package com._2491nomythic.util;

/**
 * A button that uses a digital input.
 */
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

public class DigitalInputButton extends Button {
	DigitalInput m_input;
	
	/**
	 * A button that uses a digital input.
	 * @param input The digital input.
	 */
	public DigitalInputButton (DigitalInput input) {
		m_input = input;
	}
	
	/**
	 * A button that uses a digital input.
	 * @param channel The channel of the digital input.
	 */
	public DigitalInputButton (int channel) {
		m_input = new DigitalInput(channel);
	}
	
	public boolean get() {
		return !m_input.get();
	}
	
}