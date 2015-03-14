package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.UpdateDriverstation;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ExtraSensors extends Subsystem {
	
	private PowerDistributionPanel powerDistributionPanel;
	private static ExtraSensors instance;
	
	public static ExtraSensors getInstance() {
		if (instance == null) {
			instance = new ExtraSensors();
		}
		return instance;
	}
	
	private ExtraSensors() {
		powerDistributionPanel = new PowerDistributionPanel();
	}
	
	/**
	 * 
	 * @return The power distribution panel.
	 */
	public PowerDistributionPanel getPowerDistributionPanel() {
		return powerDistributionPanel;
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new UpdateDriverstation());
	}
	
}
