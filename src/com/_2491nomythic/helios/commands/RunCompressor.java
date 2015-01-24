package com._2491nomythic.helios.commands;

public class RunCompressor extends CommandBase {

	public RunCompressor() {
		requires(compressor);
	}
	
	protected void initialize() {
		compressor.startCompressor();		
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		//Never done running compressor
		return false;
	}

	protected void end() {
		compressor.stopCompressor();
	}

	protected void interrupted() {
		end();
	}
	
}
