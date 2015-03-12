package com._2491nomythic.helios.commands.drivetrain;


import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.util.PolarCoord;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends CommandBase {
	private double currentMultiplier = 0.25;
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
		double targetMultiplier = 0.25;
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonA)) {
			targetMultiplier *= Constants.sqrt3;
		}
		if (oi.getButton(ControllerMap.DriveController, ControllerMap.FasterDriveButtonB)) {
			targetMultiplier *= Constants.sqrt3;
		}
		if(Math.abs(targetMultiplier - currentMultiplier) > 0.05) {
			if(targetMultiplier > currentMultiplier) {
				currentMultiplier += 0.05;
			}
			else {
				currentMultiplier -= 0.05;
			}
		}
		else {
			targetMultiplier = currentMultiplier;
		}
		
		
		//drivetrain.driveCartesian(oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisX) * currentMultiplier, -1.0 * oi.getAxisForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisY) * currentMultiplier, oi.getAxisForDrive(ControllerMap.TurnController, ControllerMap.TurnAxis) * currentMultiplier);
		PolarCoord coord = oi.getPlaneForDrive(ControllerMap.DriveController, ControllerMap.DriveAxisX, ControllerMap.DriveAxisY, false, true);
		coord.setR(coord.getR() * currentMultiplier);
		drivetrain.drivePolar(coord, oi.getAxisForDrive(ControllerMap.TurnController, ControllerMap.TurnAxis) * currentMultiplier);
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
