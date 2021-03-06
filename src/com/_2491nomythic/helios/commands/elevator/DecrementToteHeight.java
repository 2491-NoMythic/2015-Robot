package com._2491nomythic.helios.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Sends elevator down one tote.
 */
public class DecrementToteHeight extends CommandGroup {
	
	/**
	 * Sends elevator down one tote.
	 */
	public DecrementToteHeight() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		addSequential(new DecrementToteHeightHelper());
		addSequential(new GoToToteHeight());
		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.
		
		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
