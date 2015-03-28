package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DrivePID;
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
	private DrivePID moveToTheRight;
	private DrivePID driveUpALittle;
	private RunWithPID pickUpBin;
	private RunGrabberTime makeBinVertical;
	private DriveTime driveIntoAutoZone;
	private ElevateTime raiseToteUp;
	private DrivePID driveVerticalToToteDistance;
	private DrivePID moveInToTote;
	private DrivePID moveToTheLeft;
	private DrivePID moveIntoAutozone;
	private ElevateTime stackBin;
	private RunWithPID beginLowering;
	private ElevateTime placeDown;
	private DrivePID driveBackALittle;
	private RunWithPID moveArmUpALittle;
	private Timer timer;
	private int state;
	private DrivePID driveBack;
	private boolean placeThemDown = false;
	
	public TwoTotesAndOneBin(boolean placeTotes) {
		lowerToBin = new RunWithPID(Variables.pickUpBinPosTwoToteAuto);
		raiseToteUp = new ElevateTime(0.75, 1);
		moveToTheRight = new DrivePID(0.5, 1, 0);
		moveToTheLeft = new DrivePID(0.5, -1, 0);
		driveUpALittle = new DrivePID(0.5, 0, 1.2);
		pickUpBin = new RunWithPID(Variables.holdBinDistance);
		driveVerticalToToteDistance = new DrivePID(0.5, 0,
				6.230298764 + 0.519701236);
		moveInToTote = new DrivePID(0.5, 0, 0.519701236);
		stackBin = new ElevateTime(-0.75, 1);
		moveIntoAutozone = new DrivePID(0.5, 9.9167, 0);
		beginLowering = new RunWithPID(Variables.pickUpBinPosTwoToteAuto - 20);
		placeDown = new ElevateTime(-0.75, 1);
		driveBackALittle = new DrivePID(0.5, 0, -1.2);
		moveArmUpALittle = new RunWithPID(50);
		driveBack = new DrivePID(0.5, 0, -1);
		this.placeThemDown = placeTotes;
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
					stackBin.start();
				}
				state = 5;
				break;
			case 5:
				if (!stackBin.isRunning()) {
					raiseToteUp.start();
				}
				state = 6;
				break;
			case 6:
				if (!raiseToteUp.isRunning()) {
					moveIntoAutozone.start();
				}
				if (placeThemDown) {
					state = 7;
				}
				else {
					state = 11;
				}
				break;
			case 7:
				if (!moveIntoAutozone.isRunning() && !beginLowering.isRunning()) {
					lowerToBin.start(); // release the bin point
					placeDown.start();
				}
				state = 8;
				break;
			case 8:
				if (!lowerToBin.isRunning() && !beginLowering.isRunning()) {
					driveBackALittle.start();
				}
				state = 9;
				break;
			case 9:
				if (!driveBackALittle.isRunning()) {
					moveArmUpALittle.start();
				}
				state = 10;
				break;
			case 10:
				if (!moveArmUpALittle.isRunning()) {
					driveBack.start();
				}
				state = 11;
				break;
			case 11:
				break;
			default:
				System.out
						.println("Something's wrong in autonomous!  State is "
								+ state);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == 14 && !driveBack.isRunning();
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
		beginLowering.cancel();
		lowerToBin.cancel();
		placeDown.cancel();
		moveArmUpALittle.cancel();
	}
}
