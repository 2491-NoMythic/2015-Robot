package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class DriveIntoAutoZone extends CommandGroup {
    
    public  DriveIntoAutoZone() {
    	addSequential(new DriveTime(0.5, Constants.nullX, 3.0));
    }
}
