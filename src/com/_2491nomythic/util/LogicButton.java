package com._2491nomythic.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class LogicButton extends Button {
	
	public enum ButtonMode {
		AAndNotB,
		AOrNotB,
		AAndB,
		AOrB
	};
	
	Button a, b;
	ButtonMode mode;
	
	public LogicButton(Button a, Button b, ButtonMode mode) {
		this.a = a;
		this.b = b;
		this.mode = mode;
	}
	
	@Override
	public boolean get() {
		switch (mode) {
			case AAndNotB:
				return a.get() && !b.get();
			case AOrNotB:
				return a.get() || !b.get();
			case AAndB:
				return a.get() && b.get();
			case AOrB:
				return a.get() || b.get();
		}
		return false;
	}
	
}
