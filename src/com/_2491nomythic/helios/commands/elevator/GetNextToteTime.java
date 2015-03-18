package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.drivetrain.DriveTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An unreliable command group that is supposed to go from holding one tote to holding two totes.
 */
public class GetNextToteTime extends CommandGroup {
    
    public  GetNextToteTime() {
        addSequential(new ElevateTime(-1.0, 0.25));
        addParallel(new DriveTime(0.25, 0.0, -0.5));
        addSequential(new BottomElevator(-1.0));
        addSequential(new DriveTime(0.25, 0.0, 0.6));
        addSequential(new ElevateTime(1.0, 1.0));
    }
}
