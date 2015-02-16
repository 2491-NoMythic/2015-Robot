package com._2491nomythic.helios.commands.drivetrain;


import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends CommandBase {
	
	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Go at half speed unless the right trigger is held
		double multiplier = 0.25;
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonA)) {
			multiplier *= 2;
		}
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonB)) {
			multiplier *= 2;
		}
		drivetrain.driveCartesian(oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisX) * multiplier, oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisY) * multiplier, oi.getAxisForDrive(ControllerMap.TurnController, ControllerMap.TurnAxis) * multiplier);
		SmartDashboard.putNumber("Front Left", drivetrain.getFrontLeftMotor().get());
		SmartDashboard.putNumber("Front Right", drivetrain.getFrontRightMotor().get());
		SmartDashboard.putNumber("Front Center", drivetrain.getFrontCenterMotor().get());
		SmartDashboard.putNumber("Back Center", drivetrain.getBackCenterMotor().get());
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
