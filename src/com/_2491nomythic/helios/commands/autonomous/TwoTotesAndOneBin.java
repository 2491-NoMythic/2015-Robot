package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase; 
import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DrivePID;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.elevator.ElevateTime;
import com._2491nomythic.helios.commands.elevator.PickUpNextTote;
import com._2491nomythic.helios.commands.grabber.RunGrabberTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class TwoTotesAndOneBin extends CommandBase {
	private RunWithPID lowerToBin, lowerNearBin;
	private DrivePID driveToPickUpBin, driveToHookBin;
	private DriveTime driveBackToHookBin;
	private RunWithPID pickUpBin;
	private ElevateTime raiseToteUp;
	private DrivePID driveToSecondTote, driveToSecondToteSlow;
	private DriveTime moveIntoAutozone;
	private ElevateTime stackTote;
	private ElevateTime placeTotesDown;
	private DrivePID driveToUnhookBin;
	private RunWithPID moveArmUpALittle;
	private int state;
	private PickUpNextTote pickUpTote;
	private DrivePID driveBack;
	Timer timer;
	private boolean placeThemDown = false;
	
	public TwoTotesAndOneBin(boolean placeTotes) {
		lowerNearBin = new RunWithPID(Variables.pickUpBinPosTwoToteAuto - 10); // Start lowering the arm before we're done driving back 
		raiseToteUp = new ElevateTime(0.75, Constants.upOneToteTime); // Lift up tote
		driveToPickUpBin = new DrivePID(0.3, Constants.nullX, -1.2); // Drive back with the tote so we can pick up the bin
		lowerToBin = new RunWithPID(Variables.pickUpBinPosTwoToteAuto); // Lower arm to position to actually pick up bin
		driveToHookBin = new DrivePID(0.3, Constants.nullX, 0.75); // Drive forward to hook tote
		driveBackToHookBin = new DriveTime(-0.3, Constants.nullX, 0.5); 
		pickUpBin = new RunWithPID(Variables.holdBinDistance); // Lift the arm back up
		driveToSecondTote = new DrivePID(0.4, Constants.nullX, 7); // Drive to the second tote
		driveToSecondToteSlow = new DrivePID(0.3, Constants.nullX, 1.5); // Don't launch the second tote
		pickUpTote = new PickUpNextTote(); // Pick up the second tote
		moveIntoAutozone = new DriveTime(0.5, 2, 0); // Drive into the auto zone
		placeTotesDown = new ElevateTime(-0.75, 1); // Place the totes down
		stackTote = new ElevateTime(-0.75, 1);
		driveToUnhookBin = new DrivePID(0.5, 0, -0.5);
		moveArmUpALittle = new RunWithPID(50);
		driveBack = new DrivePID(0.5, 0, -1);
		timer = new Timer();
		this.placeThemDown = placeTotes;
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
					timer.stop();
					driveToPickUpBin.start();
					state = 2;
				}
				break;
			case 2: // Once we drive back, we can lower the hook into the bin
				if (!driveToPickUpBin.isRunning()) {
					lowerToBin.start();
					state = 3;
				}
				break;
			case 3: // We used to drive back in case the hook was in front of the bin, but that hasn't been happening.
				if (arm.getRate() < 1 && (arm.getPosition() > Variables.pickUpBinPosTwoToteAuto - 10)) {
					//driveBackToHookBin.start();
					state = 4;
				}
				break;
			case 4: // Once the hook is in the bin, drive forward to hook the bin
				if (!driveBackToHookBin.isRunning()) {
					driveToHookBin.start();
					state = 5;
				}
				break;
			case 5: // Then, start picking the bin up
				if (!driveToHookBin.isRunning()) {
					pickUpBin.start();
					state = 6;
				}
				break;
			case 6: // Once the arm is fairly high, drive to the next tote
				if (arm.getPosition() < 30) {
					driveToSecondTote.start();
					state = 7;
				}
				break;
			case 7: // Slow down so the tote doesn't go flying if we hit it
				if (!driveToSecondTote.isRunning()) {
					driveToSecondToteSlow.start();
					state = 8;
				}
				break;
			case 8: // Then, pick up the second tote
				if (!driveToSecondToteSlow.isRunning() || drivetrain.getRightEncoder().getRate() < 0.1) {
					driveToSecondToteSlow.cancel();
					pickUpTote.start();
					state = 9;
				}
				break;
			case 9: // After we pick up the second tote, drive into the auto zone
				if (!pickUpTote.isRunning()) {
					moveIntoAutozone.start();
					if (placeThemDown) {
						state = 10; // If placing stuff down is enabled, continue
					}
					else {
						state = 14; // Otherwise, go straight to the end
					}
				}
				break;
			case 10: // If we're in the auto zone, place down the tote stack and bin.
				if (!moveIntoAutozone.isRunning()) {
					lowerToBin.start();
					placeTotesDown.start();
					state = 11;
				}
				break;
			case 11: // Unhook the bin
				if (!lowerToBin.isRunning()) {
					driveToUnhookBin.start();
					state = 12;
				}
				break;
			case 12: // Move the arm up a little
				if (!driveToUnhookBin.isRunning()) {
					moveArmUpALittle.start();
					state = 13;
				}
				break;
			case 13: // Drive away from the bin
				if (!moveArmUpALittle.isRunning()) {
					driveBack.start();
					state = 14;
				}
				break;
			case 14:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == 14 && !driveBack.isRunning() && !moveIntoAutozone.isRunning();
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		driveToHookBin.cancel();
		driveToSecondTote.cancel();
		moveIntoAutozone.cancel();
		driveBack.cancel();
		driveToUnhookBin.cancel();
		lowerToBin.cancel();
		raiseToteUp.cancel();
		pickUpBin.cancel();
		stackTote.cancel();
		raiseToteUp.cancel();
		lowerToBin.cancel();
		placeTotesDown.cancel();
		moveArmUpALittle.cancel();
	}
}
