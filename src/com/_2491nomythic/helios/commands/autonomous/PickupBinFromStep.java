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
public class PickupBinFromStep extends CommandBase {
	private DriveTime backUpToStart;
	private RunArmPosition lowerToBin;
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
	public PickupBinFromStep() {
		arm.setMaxPIDSpeed(1);
		backUpToStart = new DriveTime(0.25, Constants.nullX, -0.5);
		//lowerToBin = new RunArmWithPID(Variables.pickUpBinFromStepPosition);
		lowerToBin = new RunArmPosition(1.0, Variables.pickUpBinFromStepPosition - 1);
		driveToBin = new DriveTime(0.25, Constants.nullX, 1);
		pickUpBin = new RunArmWithPID(Variables.holdBinDistance);
		makeBinVertical = new RunGrabberTime(0.5, 3.0);
		driveIntoAutoZone = new DriveTime(0.5, Constants.nullX, -1.85);
		timer = new Timer();
	}

	protected void initialize() {
		state = 0;
	}

	protected void execute() {
		switch (state) {
			case 0:
				System.out.println("1 Bin Auto: Lowering arm to bin");
				lowerToBin.start();
//				backUpToStart.start();
				state = 1;
				break;
			case 1:
				if ((!lowerToBin.isRunning() || arm.getPosition() > Variables.pickUpBinFromStepPosition - 2) && !backUpToStart.isRunning()) {
					System.out.println("1 Bin Auto: Driving to bin");
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
					System.out.println("1 Bin Auto: Picking up bin from step");
					pickUpBin.start();
					timer.reset();
					state = 4;
				}
				break;
			case 4:
				if (timer.get() > 0.25) {
					System.out.println("1 Bin Auto: Rotating hook");
					makeBinVertical.start();
					state = 5;
				}
				break;
			case 5:
				if (arm.getPosition() < 30) {
					System.out.println("1 Bin Auto: Driving to autozone");
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
