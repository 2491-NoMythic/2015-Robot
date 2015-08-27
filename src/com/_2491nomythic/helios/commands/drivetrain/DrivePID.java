package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;

/**
 * Drives the robot a specified distance in a specified direction with specified power using PID.
 */
public class DrivePID extends CommandBase {
//	private double xTargetInput;
	private double yTargetInput;
//	private PIDController xController;
	private PIDController yController;
	private double maxSpeed;
	private Timer timer;
	private double rightStartPos;
	/*
	private PIDSource xInput = new PIDSource() {
		public double pidGet() {
			return drivetrain.getCenterEncoder().getDistance();
		}
	};
	
	private PIDOutput xOutput = new PIDOutput() {
		
		private double prevSpeed = 0.0;
		
		public void pidWrite(double output) {
			if(Math.abs(output) > Math.abs(maxSpeed)) {
				if(output < 0) {
					output = -(Math.abs(maxSpeed));
				}
				else {
					output = maxSpeed;
				}
			}
			if (Math.abs(prevSpeed - output) > 0.05) {
				if (output > prevSpeed) {
					output = prevSpeed + 0.05;
				}
				else {
					output = prevSpeed - 0.05;
				}
			}
			prevSpeed = output;
			drivetrain.driveCenter(output, output);
		}
	};
	*/
	private PIDSource yInput = new PIDSource() {
		public double pidGet() {
			return drivetrain.getRightEncoder().getPosition();
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
	 * Drives the robot a specified distance in a specified direction with specified power using PID.
	 * @param maxSpeedInput the maximum speed to drive the robot.
	 * @param xTargetInput The distance to drive the robot in the x direction.
	 * @param yTargetInput The distance to drive the robot in the y direction.
	 */
	public DrivePID(double maxSpeedInput, double xTargetInput, double yTargetInput) {
		requires(drivetrain);
		maxSpeed = maxSpeedInput;
//		this.xTargetInput = xTargetInput;
		this.yTargetInput = yTargetInput;
		// For some reason, inputting an encoder as the PIDSource doesn't work properly, even if the encoder has it PIDSourceParamater set to Distance.
		// Instead, make a PIDSource that returns encoder.getDistance() in its pidGet function, and it'll work fine.
//		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, xInput, xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I + 0.01, Variables.driveyPID_D, yInput, yOutput);
//		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
		timer = new Timer();
	}
	
	public void updateSettings() {
//		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, xInput, xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, yInput, yOutput);
//		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
//		xController.setSetpoint(xTargetInput + drivetrain.getCenterEncoder().getPosition());
		yController.setSetpoint(yTargetInput + drivetrain.getRightEncoder().getPosition());
		//xController.enable();
		yController.enable();
		rightStartPos = drivetrain.getRightEncoder().getPosition();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(timer.get() > 0.2) {
			timer.reset();
			timer.stop();
			if(rightStartPos == drivetrain.getRightEncoder().getPosition() || drivetrain.getRightEncoder().getRate() > 0 != drivetrain.getCurrentRightSpeed() > 0) {
				yController.disable();
				this.cancel();
			}
		}	
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		return xController.onTarget() && yController.onTarget();
		return yController.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
//		xController.disable();
		yController.disable();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
