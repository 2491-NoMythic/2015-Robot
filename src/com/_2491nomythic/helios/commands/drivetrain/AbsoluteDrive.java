package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.util.CartesianCoord;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A drive that theoretically keeps drive directions in the same direction relative to the driver and field, not the robot. It currently is just for show because the gyro is very imprecise.
 */
public class AbsoluteDrive extends CommandBase {
	
	/**
	 * A drive that theoretically keeps drive directions in the same direction relative to the driver and field, not the robot. It currently is just for show because the gyro is very imprecise.
	 */
	public AbsoluteDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double multiplier = 0.25;
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonA)) {
			multiplier *= 2;
		}
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonB)) {
			multiplier *= 2;
		}
		CartesianCoord driveCoord = new CartesianCoord(oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisX) * multiplier, -1.0 * oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisY) * multiplier);
		drivetrain.driveAbsolute(driveCoord.getPolar(), oi.getAxisForDrive(ControllerMap.TurnController, ControllerMap.TurnAxis) * multiplier);
		SmartDashboard.putNumber("Front Left", drivetrain.getFrontLeftMotor().get());
		SmartDashboard.putNumber("Front Right", drivetrain.getFrontRightMotor().get());
		SmartDashboard.putNumber("Front Center", drivetrain.getFrontCenterMotor().get());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		drivetrain.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
