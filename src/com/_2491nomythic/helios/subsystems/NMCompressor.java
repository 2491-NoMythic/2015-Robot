package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.RunCompressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NMCompressor extends Subsystem {
	private Compressor compressor;
	private boolean isEnabled = false;
	private static NMCompressor instance;
	
	public static NMCompressor getInstance() {
		if (instance == null) {
			instance = new NMCompressor();
		}
		return instance;
	}
	
	private NMCompressor() {
		compressor = new Compressor();
	}
	
	public void startCompressor() {
		compressor.start();
		isEnabled = true;
	}
	
	public void stopCompressor() {
		compressor.stop();
		isEnabled = false;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new RunCompressor());	
	}
	
}
