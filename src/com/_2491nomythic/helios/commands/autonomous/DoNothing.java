package com._2491nomythic.helios.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An autonomous that does nothing.
 */
public class DoNothing extends Command {
    
	/**
	 * An autonomous that does nothing.
	 */
    public  DoNothing() {
    }
    
	protected void initialize() {}

	protected void execute() {}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {}

	protected void interrupted() {}
}
