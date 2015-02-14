package com._2491nomythic.helios.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com._2491nomythic.helios.commands.drivetrain.DriveDistance;

/**
 *
 */
public class PickUpNextTote extends CommandGroup {
    
    public  PickUpNextTote() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.True));
    	addSequential(new DriveDistance(0.5, 0, -0.167));
    	addSequential(new DecrementToteHeight());
    	addSequential(new DriveDistance(0.5, 0, 0.167));
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.False));

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
