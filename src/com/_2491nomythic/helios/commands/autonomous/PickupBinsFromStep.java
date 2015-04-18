package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase;
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
	private DriveTime backUpToStart, driveToBin, driveIntoAutoZone, driveToNextBin, driveToBinAgain;
	private RunArmWithPID lowerToBin, pickUpBin, putDownBin;
	private RunGrabberTime makeBinVertical, unhookBin;
	private int state; // 0 = Before start, 1 = lowering arm, 2 = driving forward,3 = waiting to pick up bin
					   // 4 = picking up bin, 5 = rotating bin, 6 = driving to auto zone
	private Timer timer;
	
	/**
	 * An autonomous that picks up two recycling containers from the step and places them into the auto zone.
	 */
	public PickupBinsFromStep() {
		backUpToStart = new DriveTime(0.25, Constants.nullX, -0.5);
		lowerToBin = new RunArmWithPID(Variables.pickUpBinFromStepPosition);
		driveToBin = new DriveTime(0.25, Constants.nullX, 0.8);
		pickUpBin = new RunArmWithPID(Variables.holdBinDistance);
		makeBinVertical = new RunGrabberTime(0.5, 1.0);
		putDownBin = new RunArmWithPID(Variables.putDownBinBackwards);
		unhookBin = new RunGrabberTime(-0.5, 2.0);
		driveToNextBin = new DriveTime(0.5, -1.7, Constants.nullY); //CHANGE THIS TO -1 TO GO LEFT TO THE NEXT BIN
		driveToBinAgain = new DriveTime(0.25, Constants.nullX, 0.5);
		driveIntoAutoZone = new DriveTime(0.5, Constants.nullX, -1.85);
		timer = new Timer();
	}

	protected void initialize() {
		arm.setMaxPIDSpeed(1.0);
		state = 0;
	}

	protected void execute() {
		System.out.println(state);
		switch (state) {
			case 0:
				lowerToBin.start();
				state = 1;
				break;
			case 1:
				if (!lowerToBin.isRunning() && !backUpToStart.isRunning()) {
					driveToBin.start();
					state = 2;
				}
				break;
			case 2:
				if (!driveToBin.isRunning()) {
					state = 3;
					timer.start();
					timer.reset();
				}
				break;
			case 3:
				if (timer.get() > 0.1) {
					arm.setMaxPIDSpeed(0.5);
					putDownBin.start();
					timer.reset();
					state = 4;
				}
				break;
			case 4:
				if (timer.get() > 1.5) {
					backUpToStart.start();
					makeBinVertical.start();
					state = 5;
				}
				break;
			case 5:
				if (!putDownBin.isRunning() && !makeBinVertical.isRunning()) {
					unhookBin.start();
					state = 6;
				}
				break;
			case 6:
				if (!unhookBin.isRunning()) {
					arm.setMaxPIDSpeed(1.0);
					lowerToBin.start();
					timer.reset();
					state = 7;
				}
				break;
			case 7:
				if (timer.get() > 0.5) {
					driveToNextBin.start();
					state = 8;
				}
				break;
			case 8:
				if (!driveToNextBin.isRunning() && !lowerToBin.isRunning()) {
					driveToBinAgain.start();
					state = 9;
				}
				break;
			case 9:
				if (!driveToBinAgain.isRunning()) {
					pickUpBin.start();
					timer.reset();
					state = 10;
				}
				break;
			case 10:
				if (timer.get() > 0.25) {
					makeBinVertical.start();
					state = 11;
				}
				break;
			case 11:
				if (arm.getPosition() < 30) {
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
	}

	protected void interrupted() {
		backUpToStart.cancel();
		lowerToBin.cancel();
		driveToBin.cancel();
		pickUpBin.cancel();
		makeBinVertical.cancel();
	}
}
