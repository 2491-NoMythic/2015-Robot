package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.drivetrain.DriveDistance;
import com._2491nomythic.helios.commands.elevator.DecrementToteHeight;
import com._2491nomythic.helios.commands.elevator.GoToToteHeight;
import com._2491nomythic.helios.commands.elevator.IncrementToteHeight;
import com._2491nomythic.helios.commands.elevator.UnderLipStatus;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TripleStackAuto extends CommandGroup {
    
    public  TripleStackAuto() {
    	Variables.elevatorTarget = 0;
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.False));
    	addParallel(new IncrementToteHeight());
    	addSequential(new DriveDistance(0.5, Constants.nullX, -1));
    	addSequential(new DriveDistance(0.5, -6.75 , Constants.nullY));
    	addSequential(new DriveDistance(0.5, Constants.nullX, 1));
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.True));
    	addSequential(new DriveDistance(0.5, Constants.nullX, -0.16667));
    	addSequential(new DecrementToteHeight());
    	addSequential(new DriveDistance(0.5, Constants.nullX, 0.16667));
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.False));
    	addParallel(new IncrementToteHeight());
    	addSequential(new DriveDistance(0.5, Constants.nullX, -1));
    	addSequential(new DriveDistance(0.5, -6.75, Constants.nullY));
    	addSequential(new DriveDistance(0.5, Constants.nullX, 1));
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.True));
    	addSequential(new DriveDistance(0.5, Constants.nullX, -0.16667));
    	addSequential(new DecrementToteHeight());
    	addSequential(new DriveDistance(0.5, Constants.nullX, 0.16667));
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.False));
    	addSequential(new IncrementToteHeight());
    	addSequential(new DriveDistance(0.5, Constants.nullX, 12.6667));
    	addSequential(new DecrementToteHeight());
    	addSequential(new UnderLipStatus(UnderLipStatus.switchType.True));
    	addSequential(new DriveDistance(0.5, Constants.nullX, -0.5));
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
