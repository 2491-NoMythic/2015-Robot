package com._2491nomythic.helios.autonomous;

import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DriveDistance;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class autonomous extends CommandGroup {
    
    public  autonomous() {
    	addSequential(new RunWithPID(Variables.horizontalAForewardPos));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, Variables.pickup1stBinYDistance));
    	addSequential(new RunWithPID(Variables.verticalArmPos)); //may be made a parallel if the arm is brought up fast enough
    	addSequential(new DriveDistance(Variables.driveBackToOrigPosPower, Constants.nullX, -(Variables.pickup1stBinYDistance)));
    	addSequential(new RunWithPID(Variables.recyclingContainerReleasePos));
    	addSequential(new DriveDistance(Variables.unhookBinPower, Variables.unhookBinXDistance, Variables.unhookBinYDistance));
    	addSequential(new RunWithPID(Variables.horizontalAForewardPos));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, Variables.pickup2ndBinYDistance)); //may be made a parallel if the arm can be brought down fast enough
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Variables.pickup2ndBinXDistance, Constants.nullY));
    	addSequential(new RunWithPID(Variables.verticalArmPos));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, -(Variables.pickup2ndBinXDistance), Constants.nullY)); //may be made a parallel if the arm is brought up fast enough
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, -(Variables.pickup2ndBinYDistance)));
    	addSequential(new RunWithPID(Variables.recyclingContainerReleasePos));
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
