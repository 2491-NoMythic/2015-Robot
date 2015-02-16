package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DriveDistance;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class DriveIntoAutoZone extends CommandGroup {
    
    public  DriveIntoAutoZone() {
    	addSequential(new DriveTime(0.5, 0.0, -2.0));
    }
}
