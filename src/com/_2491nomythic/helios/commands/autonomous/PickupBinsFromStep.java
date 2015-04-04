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
	private DriveTime backUpToStart;
	private RunArmWithPID lowerToBin;
	private DriveTime driveToBin;
	private RunArmWithPID pickUpBin;
	private RunGrabberTime makeBinVertical;
	private DriveTime driveIntoAutoZone;
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
		makeBinVertical = new RunGrabberTime(1.0, 3.0);
		driveIntoAutoZone = new DriveTime(0.5, Constants.nullX, -1.85);
		timer = new Timer();
	}

	protected void initialize() {
		state = 0;
	}

	protected void execute() {
		switch (state) {
			case 0:
				lowerToBin.start();
//				backUpToStart.start();
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
				if (timer.get() > 0.5) {
					pickUpBin.start();
					timer.reset();
					state = 4;
				}
				break;
			case 4:
				if (timer.get() > 0.25) {
					makeBinVertical.start();
					state = 5;
				}
				break;
			case 5:
				if (arm.getPosition() < 30) {
					driveIntoAutoZone.start();
					state = 6;
				}
				break;
			case 6:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
		}
	}

	protected boolean isFinished() {
		return state == 6 && !makeBinVertical.isRunning() && !pickUpBin.isRunning();
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
