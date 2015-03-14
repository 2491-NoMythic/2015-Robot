package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class DriveWithArm extends CommandBase {
	private double armStartingPosition;
	private double drivetrainStartingPosition;
	private double maxSpeed;	
	private PIDOutput yOutput = new PIDOutput() {
		
		public void pidWrite(double output) {
			if(Math.abs(output) > Math.abs(maxSpeed)) {
				if(output < 0) {
					output = -(Math.abs(maxSpeed));
				}
				else {
					output = maxSpeed;
				}
			}
			drivetrain.driveRight(output);
			drivetrain.driveLeft(output);
		}
	};
	private PIDController yController;
	
	public DriveWithArm() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(drivetrain);
		
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, drivetrain.getCenterEncoder(), yOutput);
		yController.setAbsoluteTolerance(0.1);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		armStartingPosition = Math.toRadians(arm.getPosition());
		drivetrainStartingPosition = drivetrain.getRightEncoder().getDistance();
		yController.enable();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double radiansMoved = Math.toRadians(arm.getPosition()) - armStartingPosition;
		double feetMoved = radiansMoved * Constants.armLength;
		double horizontalFeetMoved = feetMoved * Math.cos(radiansMoved);
		double driveTarget = drivetrainStartingPosition - horizontalFeetMoved;
		yController.setSetpoint(driveTarget);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		yController.disable();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
