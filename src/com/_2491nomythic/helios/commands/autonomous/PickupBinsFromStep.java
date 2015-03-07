package com._2491nomythic.helios.commands.autonomous;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.arm.RunWithPID;
import com._2491nomythic.helios.commands.drivetrain.DriveDistance;
import com._2491nomythic.helios.commands.drivetrain.DriveTime;
import com._2491nomythic.helios.commands.grabber.RunGrabberTime;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class PickupBinsFromStep extends CommandBase {
	private RunWithPID lowerToBin;
	private DriveTime driveToBin;
	private RunWithPID pickUpBin;
	private RunGrabberTime makeBinVertical;
	private int state; // 0 = Before start, 1 = lowering arm, 2 = driving forward, 3 = picking up bin, 4 = rotating bin
	private Timer timer;
	
	public PickupBinsFromStep() {
		lowerToBin = new RunWithPID(Variables.pickUpBinFromStepPosition);
		driveToBin = new DriveTime(0.25, Constants.nullX, 0.1);
		pickUpBin = new RunWithPID(Variables.holdBinDistance);
		makeBinVertical = new RunGrabberTime(1.0, 2.0);
		timer = new Timer();
	}

	protected void initialize() {
		state = 0;
	}

	protected void execute() {
		switch (state) {
			case 0:
				lowerToBin.start();
				state = 1;
				break;
			case 1:
				if (!lowerToBin.isRunning()) {
					driveToBin.start();
					state = 2;
				}
				break;
			case 2:
				if (!driveToBin.isRunning()) {
					pickUpBin.start();
					state = 3;
					timer.start();
					timer.reset();
				}
				break;
			case 3:
				if (timer.get() > 0.25) {
					makeBinVertical.start();
					state = 4;
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Something's wrong in autonomous!  State is " + state);
		}
	}

	protected boolean isFinished() {
		return state == 4 && !makeBinVertical.isRunning() && !pickUpBin.isRunning();
	}

	protected void end() {		
	}

	protected void interrupted() {
		lowerToBin.cancel();
		driveToBin.cancel();
		pickUpBin.cancel();
		makeBinVertical.cancel();
	}
}
