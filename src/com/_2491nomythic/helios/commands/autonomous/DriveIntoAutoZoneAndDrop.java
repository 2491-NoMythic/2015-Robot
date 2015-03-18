package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.elevator.BottomElevator;
import com._2491nomythic.helios.commands.elevator.ElevateTime;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous that drives into the auto zone with a yellow tote and drops it.
 */

public class DriveIntoAutoZoneAndDrop extends CommandGroup {
    
    public  DriveIntoAutoZoneAndDrop() {
    	addSequential(new ElevateTime(1.0, 4.0));
    	addSequential(new DriveTime(0.5, Constants.nullX, 2.5));
    	addSequential(new BottomElevator(-1.0, 6.0));
    }
}
