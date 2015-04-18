package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase; 
import com._2491nomythic.helios.commands.arm.RunArmWithPID;
import com._2491nomythic.helios.commands.drivetrain.DrivePID;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.elevator.ElevateTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class OneToteAndOneBin extends CommandBase {
	private RunArmWithPID lowerToBin, lowerNearBin;
	private DrivePID driveToPickUpBin, driveToHookBin;
	private DrivePID driveToClearScoringPlatform;
	private DriveTime driveBackToHookBin;
	private RunArmWithPID pickUpBin;
	private ElevateTime raiseToteUp;
	private DriveTime moveIntoAutozone;
	private boolean shouldDriveForward;
	private int state;
	Timer timer;
	public OneToteAndOneBin(boolean shouldDriveForward) {
		this.shouldDriveForward = shouldDriveForward;
		lowerNearBin = new RunArmWithPID(Variables.pickUpBinPosTwoToteAuto - 5); // Start lowering the arm before we're done driving back 
		raiseToteUp = new ElevateTime(0.75, Constants.upOneToteTime); // Lift up tote
		driveToPickUpBin = new DrivePID(0.5, Constants.nullX, -1.3); // Drive back with the tote so we can pick up the bin
		lowerToBin = new RunArmWithPID(Variables.pickUpBinPosTwoToteAuto); // Lower arm to position to actually pick up bin
		driveToHookBin = new DrivePID(0.3, Constants.nullX, -0.75); // Drive back to hook tote
		driveBackToHookBin = new DriveTime(-0.3, Constants.nullX, 0.5); 
		pickUpBin = new RunArmWithPID(Variables.holdBinDistance); // Lift the arm back up
		driveToClearScoringPlatform = new DrivePID(0.4, Constants.nullX, 8.0);
		moveIntoAutozone = new DriveTime(0.5, 4.0, 0); // Drive into the auto zone
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (state) {
			case 0: // Start by lifting the tote and starting the arm lowering process
				lowerNearBin.start();
				raiseToteUp.start();
				timer.start();
				timer.reset();
				state = 1;
				break;
			case 1: // After 0.1 seconds, the tote will be off the ground so we can start backing up.
				if (timer.get() > 0.1) {
					timer.reset();
					driveToPickUpBin.start();
					state = 2;
				}
				break;
			case 2: // Once we drive back, we can lower the hook into the bin
				if (!driveToPickUpBin.isRunning() || timer.get() > 1.8) {
					timer.stop();
					lowerToBin.start();
					state = 3;
				}
				break;
			case 3: // We used to drive back in case the hook was in front of the bin, but that hasn't been happening.
				if (arm.getRate() < 1 && (arm.getPosition() > Variables.pickUpBinPosTwoToteAuto - 20)) {
					System.out.println(arm.getRate());
					state = 4;
				}
				break;
			case 4: // Once the hook is in the bin, drive forward to hook the bin
				if (!driveBackToHookBin.isRunning()) {
					driveToHookBin.start();
					timer.start();
					timer.reset();
					state = 5;
				}
				break;
			case 5: // Then, start picking the bin up
				if (!driveToHookBin.isRunning() || timer.get() > 0.5) {
					pickUpBin.start();
					timer.stop();
					state = 6;
				}
				break;
			case 6: // Once the arm is fairly high, drive to the next tote
				if (arm.getPosition() < 30) {
					if (shouldDriveForward) {
						driveToClearScoringPlatform.start();
					}
					state = 7;
				}
				break;
			case 7:
				if (!driveToClearScoringPlatform.isRunning()) {
					moveIntoAutozone.start();
					state = 8;
				}
				break;
			case 8:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == 8 && !moveIntoAutozone.isRunning();
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		lowerToBin.cancel();
		lowerNearBin.cancel();
		driveToHookBin.cancel();
		driveBackToHookBin.cancel();
		driveToPickUpBin.cancel();
		driveToClearScoringPlatform.cancel();
		pickUpBin.cancel();
		raiseToteUp.cancel();
		moveIntoAutozone.cancel();
	}
}
