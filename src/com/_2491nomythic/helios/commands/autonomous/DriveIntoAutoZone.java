package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.elevator.ElevateTime;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous that drives into the auto zone with a yellow tote.
 */
public class DriveIntoAutoZone extends CommandGroup {
    
    public  DriveIntoAutoZone() {
    	addSequential(new ElevateTime(1.0, 4.0));
    	addSequential(new DriveTime(0.5, Constants.nullX, 2.5));
    }
}
