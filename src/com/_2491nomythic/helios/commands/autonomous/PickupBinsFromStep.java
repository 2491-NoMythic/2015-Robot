package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.arm.RunArmPosition;
import com._2491nomythic.helios.commands.arm.RunArmWithPID;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.grabber.RunGrabberTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Timer;

/**
 * An autonomous that picks up two recycling containers from the step and places them into the auto zone.
 */
public class PickupBinsFromStep extends CommandBase {
	private DriveTime backUpToStart, driveToBin, driveFirstBinIntoAutoZone, driveToUnhookBin, driveIntoAutoZone, driveToNextBin, driveToBinAgain;
	private RunArmPosition lowerToBin;
	private RunArmWithPID pickUpBin, putDownBin;
	private RunGrabberTime makeBinVertical, unhookBin, hooksBackInPlace;
	private int state; // 0 = Before start, 1 = lowering arm, 2 = driving forward,3 = waiting to pick up bin
					   // 4 = picking up bin, 5 = rotating bin, 6 = driving to auto zone
	private Timer timer;
	
	/**
	 * An autonomous that picks up two recycling containers from the step and places them into the auto zone.
	 */
	public PickupBinsFromStep() {
		backUpToStart = new DriveTime(0.25, Constants.nullX, -0.5);
		lowerToBin = new RunArmPosition(1.0, Variables.pickUpBinFromStepPosition - 1);
		driveToBin = new DriveTime(0.25, Constants.nullX, 0.8);
		pickUpBin = new RunArmWithPID(Variables.holdBinDistance);
		makeBinVertical = new RunGrabberTime(0.5, 2.5);
		putDownBin = new RunArmWithPID(Variables.putDownBinBackwards);
		driveFirstBinIntoAutoZone = new DriveTime(0.5, -1.0, -1.0);
		unhookBin = new RunGrabberTime(-1.0, 1.9);
		hooksBackInPlace = new RunGrabberTime(0.5, 1.0);
		driveToUnhookBin = new DriveTime(0.5, Constants.nullX, -0.25);
		driveToNextBin = new DriveTime(0.5, -1.6, 0.9); 
		driveToBinAgain = new DriveTime(0.25, Constants.nullX, 0.5);
		driveIntoAutoZone = new DriveTime(0.5, Constants.nullX, -1.85);
		timer = new Timer();
	}

	protected void initialize() {
		arm.setMaxPIDSpeed(1.0);
		arm.setMaxAccleration(0.25);
		state = 0;
	}

	protected void execute() {
		System.out.println(state);
		switch (state) {
			case 0:
				System.out.println("State:" + "  " + state + " Lowering Arm To Bin");
				lowerToBin.start();
				state = 1;
				break;
			case 1:
				if ((!lowerToBin.isRunning() || arm.getPosition() > Variables.pickUpBinFromStepPosition - 2) && !backUpToStart.isRunning()) {
					System.out.println("Two Bin Auto: Driving To Bin");
					driveToBin.start();
					state = 2;
				}
				break;
			case 2:
				if (!driveToBin.isRunning()) {
					System.out.println("Two Bin Auto: Reseting the Timer");
					state = 3;
					timer.start();
					timer.reset();
				}
				break;
			case 3:
				if (timer.get() > 0.1) {
					arm.setMaxPIDSpeed(1.0);
					System.out.println("Two Bin Auto: Setting Bin Down");
					putDownBin.start();
					timer.reset();
					state = 4;
				}
				break;
			case 4:
				if (arm.getPosition() < 10) {
					arm.setMaxPIDSpeed(0.75);
				}
				if (arm.getPosition() < -30) {
					arm.setMaxPIDSpeed(1.0);
				}
				if (timer.get() > 1.5) {
					System.out.println("Two Bin Auto: Backing Up To Starting Position, Running Grabber To Make the Bin Vertical & Driving bthe First Bin Into The Autzone");
					backUpToStart.start();
					makeBinVertical.start();
					driveFirstBinIntoAutoZone.start();
					state = 5;
				}
				break;
			case 5:
				if (arm.getPosition() < 10) {
					arm.setMaxPIDSpeed(0.5);
				}
				if (arm.getPosition() < -30) {
					arm.setMaxPIDSpeed(1.0);
				}
				if ((!putDownBin.isRunning() || arm.getPosition() < Variables.putDownBinBackwards + 2) && !makeBinVertical.isRunning()) {
					System.out.println("Two Bin Auto: Unhooking the Bin");
					unhookBin.start();
					state = 6;
				}
				break;
			case 6:
				if (!unhookBin.isRunning()) {
					System.out.println("Two Bin Auto: Driving to unhook the bin");
					driveToUnhookBin.start();
					arm.setMaxPIDSpeed(1.0);
					lowerToBin.start();
					timer.reset();
					state = 7;
				}
				break;
			case 7:
				if (timer.get() > 0.5 && !driveToUnhookBin.isRunning()) {
					System.out.println("Two Bin Auto: Putting hooks back in place and driving to the next bin");
					hooksBackInPlace.start();
					driveToNextBin.start();
					state = 8;
				}
				break;
			case 8:
				if (!driveToNextBin.isRunning() && !lowerToBin.isRunning()) {
					System.out.println("Two Bin Auto: Driving up to the second bin");
					driveToBinAgain.start();
					state = 9;
				}
				break;
			case 9:
				if (!driveToBinAgain.isRunning()) {
					System.out.println("Two Bin Auto: Picking up the bin");
					pickUpBin.start();
					timer.reset();
					state = 10;
				}
				break;
			case 10:
				if (timer.get() > 0.25) {
					System.out.println("Two Bin Auto: Making the bin vertical");
					makeBinVertical.start();
					state = 11;
				}
				break;
			case 11:
				if (arm.getPosition() < 30) {
					System.out.println("Two Bin Auto: Driving into the autozone");
					driveIntoAutoZone.start();
					state = 12;
				}
				break;
			case 12:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
		}
	}

	protected boolean isFinished() {
		return state == 12 && !makeBinVertical.isRunning() && !pickUpBin.isRunning();
	}

	protected void end() {
		arm.setMaxAccleration(0.05);
	}

	protected void interrupted() {
		backUpToStart.cancel();
		lowerToBin.cancel();
		driveToBin.cancel();
		pickUpBin.cancel();
		makeBinVertical.cancel();
		end();
	}
}
