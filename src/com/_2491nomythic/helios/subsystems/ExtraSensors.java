package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.UpdateDriverstation;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Extra sensors that don't fit into any other subsystem.
 */
public class ExtraSensors extends Subsystem {
	
	private PowerDistributionPanel powerDistributionPanel;
	private static ExtraSensors instance;
	
	public static ExtraSensors getInstance() {
		if (instance == null) {
			instance = new ExtraSensors();
		}
		return instance;
	}
	
	/**
	 * Extra sensors that don't fit into any other subsystem.
	 */
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
