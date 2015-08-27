package com._2491nomythic.util.components.hardware;

import com._2491nomythic.util.components.interfaces.Motor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class HardwareMotor implements Motor {
	
	private final SpeedController controller;
	
	public static HardwareMotor createWithCANTalon(int channel) {
		return new HardwareMotor(new CANTalon(channel));
	}
	
	public static HardwareMotor createWithTalon(int channel) {
		return new HardwareMotor(new Talon(channel));
	}
	
	public static HardwareMotor createWithJaguar(int channel) {
		return new HardwareMotor(new Jaguar(channel));
	}
	
	public HardwareMotor(SpeedController controller) {
		this.controller = controller;
	}

	@Override
	public void setSpeed(double speed) {
		controller.set(speed);
	}

	@Override
	public double getSpeed() {
		return controller.get();
	}
	
}
