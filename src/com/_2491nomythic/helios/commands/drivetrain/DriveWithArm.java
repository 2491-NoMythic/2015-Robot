package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Adjusts the location of the chassis while moving the arm to compensate for horizontal movement of the arm.
 */
public class DriveWithArm extends CommandBase {
	private double armStartingPosition;
	private double armStartingHorizontalPosition;
	private double drivetrainStartingPosition;
	private double maxSpeed;	
	private PIDSource yInput = new PIDSource() {
		public double pidGet() {
			return drivetrain.getRightEncoder().getDistance();
		}
	};
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
	
	/**
	 * Adjusts the location of the chassis while moving the arm to compensate for horizontal movement of the arm.
	 */
	public DriveWithArm() {
		requires(drivetrain);
		maxSpeed = 0.5;
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, yInput, yOutput);
		yController.setAbsoluteTolerance(0.1);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		armStartingPosition = Math.toRadians(arm.getPosition());
		armStartingHorizontalPosition = Constants.armLength * Math.sin(armStartingPosition);
		drivetrainStartingPosition = drivetrain.getRightEncoder().getDistance();
		yController.setSetpoint(drivetrainStartingPosition);
		yController.enable();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Calculate the horizontal distance the arm has moved and set the drivetrain setpoint to that distance.
		double armPosRadions = Math.toRadians(arm.getPosition());
		double armDistanceFromZero = Constants.armLength * Math.sin(armPosRadions);
		double horizontalFeetMoved = armDistanceFromZero - armStartingHorizontalPosition;
		double driveTarget = drivetrainStartingPosition - horizontalFeetMoved;
		System.out.println("Horizontal Feet Moved: " + horizontalFeetMoved);
		yController.setSetpoint(driveTarget);
		
		// Drive the x axis because this requires drivetrain and will cancel the normal drive command.
		double xSpeed = oi.getAxis(ControllerMap.DriveController, ControllerMap.DriveAxisX);
		drivetrain.driveCenter(xSpeed, xSpeed);
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
