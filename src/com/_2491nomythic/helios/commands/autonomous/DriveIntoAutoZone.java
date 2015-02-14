package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class DriveIntoAutoZone extends CommandGroup {
    
    public  DriveIntoAutoZone() {
    	addSequential(new DriveTime(0.5, 1.0, 0.0));
    }
}
