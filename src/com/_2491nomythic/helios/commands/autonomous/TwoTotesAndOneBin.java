package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.elevator.ElevateTime;
import com._2491nomythic.helios.commands.grabber.RunGrabberTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class TwoTotesAndOneBin extends CommandBase {
	private DriveTime backUpToStart;
	private RunWithPID lowerToBin;
	private DriveTime driveToBin;
	private RunWithPID pickUpBin;
	private RunGrabberTime makeBinVertical;
	private DriveTime driveIntoAutoZone;
	private ElevateTime moveOnTopOfBin;
	private Timer timer;
	private int state;
	
    public TwoTotesAndOneBin() {
    	lowerToBin = new RunWithPID(Variables.pickUpBinPosTwoToteAuto);
    	moveOnTopOfBin = new ElevateTime(0.75, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    		case 0:
    			lowerToBin.start();
    			moveOnTopOfBin.start();
    			state = 1;
    			break;
    		case 1:
    			if(!lowerToBin.isRunning() && !moveOnTopOfBin.isRunning()) {
    				
    			}
    			
    			
    			
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
