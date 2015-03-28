package com._2491nomythic.helios.commands.elevator;


import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpNextTote extends CommandGroup {

    public PickUpNextTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addSequential(new BottomElevator(-0.5));
    	addSequential(new ElevateTime(0.5, Constants.upOneToteTime));
    }
}
