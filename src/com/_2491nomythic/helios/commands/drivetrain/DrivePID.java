package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Drives the robot a specified distance in a specified direction with specified power using PID.
 */
public class DrivePID extends CommandBase {
	private double xTargetInput;
	private double yTargetInput;
	private PIDController xController;
	private PIDController yController;
	private double maxSpeed;
	
	private PIDOutput xOutput = new PIDOutput() {
		
		public void pidWrite(double output) {
			if(Math.abs(output) > Math.abs(maxSpeed)) {
				if(output < 0) {
					output = -(Math.abs(maxSpeed));
				}
				else {
					output = maxSpeed;
				}
			}
			drivetrain.driveCenter(output, output);
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
	
	/**
	 * 
	 * @param maxSpeedInput the maximum speed to drive the robot.
	 * @param xTargetInput The distance to drive the robot in the x direction.
	 * @param yTargetInput The distance to drive the robot in the y direction.
	 */
	public DrivePID(double maxSpeedInput, double xTargetInput, double yTargetInput) {
		requires(drivetrain);
		maxSpeed = maxSpeedInput;
		this.xTargetInput = xTargetInput;
		this.yTargetInput = yTargetInput;
		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, drivetrain.getLeftEncoder(), xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, drivetrain.getCenterEncoder(), yOutput);
		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
	}
	
	public void updateSettings() {
		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, drivetrain.getLeftEncoder(), xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, drivetrain.getCenterEncoder(), yOutput);
		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		xController.setSetpoint(xTargetInput + drivetrain.getCenterEncoder().getDistance());
		yController.setSetpoint(yTargetInput + drivetrain.getRightEncoder().getDistance());
		xController.enable();
		yController.disable();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return xController.onTarget() && yController.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		xController.enable();
		yController.disable();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
