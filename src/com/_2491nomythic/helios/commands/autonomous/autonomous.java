package com._2491nomythic.helios.commands.autonomous;

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
    	addSequential(new RunWithPID(Variables.binPickup));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, Variables.pickupBinYDistance));
    	addSequential(new RunWithPID(Variables.verticalArmPos)); //may be made a parallel if the arm is brought up fast enough
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, -(Variables.pickupBinYDistance)));
    	addSequential(new RunWithPID(Variables.recyclingContainerReleasePos));
    	addSequential(new DriveDistance(Variables.unhookBinPower, Constants.nullX, Variables.unhookBinYDistance));
    	addSequential(new RunWithPID(Variables.verticalArmPos));
    	addParallel(new RunWithPID(Variables.binPickup));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Variables.pickup2ndBinXDistance, Constants.nullY)); //may be made a parallel if the arm can be brought down fast enough
    	addSequential(new RunWithPID(Variables.verticalArmPos));
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Variables.driveNextToBinXDistance, Constants.nullY)); //may be made a parallel if the arm is brought up fast enough
    	addSequential(new DriveDistance(Variables.pickupBinDrivePower, Constants.nullX, Variables.driveNextToBinYDistance));
    }
}
