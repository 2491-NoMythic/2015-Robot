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
	private RunWithPID lowerToBin;
	private DrivePID driveUpALittle;
	private RunWithPID pickUpBin;
	private ElevateTime raiseToteUp;
	private DrivePID driveVerticalToToteDistance;
	private DrivePID moveIntoAutozone;
	private ElevateTime stackBin;
	private ElevateTime placeDown;
	private DrivePID driveBackALittle;
	private RunWithPID moveArmUpALittle;
	private int state;
	private PickUpNextTote pickUp;
	private DrivePID driveBack;
	private boolean placeThemDown = false;
	
	public TwoTotesAndOneBin(boolean placeTotes) {
		lowerToBin = new RunWithPID(Variables.pickUpBinPosTwoToteAuto);
		raiseToteUp = new ElevateTime(0.75, 1);
		driveUpALittle = new DrivePID(0.5, 0, 1.2);
		pickUpBin = new RunWithPID(Variables.holdBinDistance);
		driveVerticalToToteDistance = new DrivePID(0.5, 0,
				6.230298764 + 0.519701236);
		stackBin = new ElevateTime(-0.75, 1);
		moveIntoAutozone = new DrivePID(0.5, 9.9167, 0);
		placeDown = new ElevateTime(-0.75, 1);
		driveBackALittle = new DrivePID(0.5, 0, -1.2);
		moveArmUpALittle = new RunWithPID(50);
		driveBack = new DrivePID(0.5, 0, -1);
		pickUp = new PickUpNextTote();
		this.placeThemDown = placeTotes;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(state);
		switch (state) {
			case 0:
				lowerToBin.start();
				raiseToteUp.start();
				state = 1;
				break;
			case 1:
				if (!lowerToBin.isRunning() && !raiseToteUp.isRunning()) {
					driveUpALittle.start();
				}
				state = 2;
				break;
			case 2:
				if (!driveUpALittle.isRunning()) {
					pickUpBin.start();
				}
				state = 3;
				break;
			case 3:
				if (!pickUpBin.isRunning()) {
					driveVerticalToToteDistance.start();
				}
				state = 4;
				break;
			case 4:
				if (!driveVerticalToToteDistance.isRunning()) {
					pickUp.start();
				}
				state = 5;
				break;
			case 5:
				if (!pickUp.isRunning()) {
					moveIntoAutozone.start();
				}
				if (placeThemDown) {
					state = 6;
				}
				else {
					state = 10;
				}
				break;
			case 6:
				if (!moveIntoAutozone.isRunning()) {
					lowerToBin.start(); // release the bin point
					placeDown.start();
				}
				state = 7;
				break;
			case 7:
				if (!lowerToBin.isRunning()) {
					driveBackALittle.start();
				}
				state = 8;
				break;
			case 8:
				if (!driveBackALittle.isRunning()) {
					moveArmUpALittle.start();
				}
				state = 9;
				break;
			case 9:
				if (!moveArmUpALittle.isRunning()) {
					driveBack.start();
				}
				state = 10;
				break;
			case 10:
				break;
			default:
				System.out
						.println("Something's wrong in autonomous!  State is "
								+ state);
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
		driveUpALittle.cancel();
		driveVerticalToToteDistance.cancel();
		moveIntoAutozone.cancel();
		driveBack.cancel();
		driveBackALittle.cancel();
		lowerToBin.cancel();
		raiseToteUp.cancel();
		pickUpBin.cancel();
		stackBin.cancel();
		raiseToteUp.cancel();
		lowerToBin.cancel();
		placeDown.cancel();
		moveArmUpALittle.cancel();
	}
}
