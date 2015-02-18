package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class DrivePID extends CommandBase {
	private double xTargetInput;
	private double yTargetInput;
	private PIDController xController;
	private PIDController yController;
	private int centerEncoder = drivetrain.getCenterEncoder().get();
	private int leftEncoder = drivetrain.getLeftEncoder().get();
	private int rightEncoder = drivetrain.getRightEncoder().get();
	
	private PIDOutput xOutput = new PIDOutput() {
		
		public void pidWrite(double output) {
			drivetrain.driveCenter(output + centerEncoder, output + centerEncoder);
		}
	};
	private PIDOutput yOutput = new PIDOutput() {
		
		public void pidWrite(double output) {
			drivetrain.driveRight(output + rightEncoder);
			drivetrain.driveLeft(output + leftEncoder);
		}
	};
	
	public DrivePID(double xTargetInput, double yTargetInput) {
		requires(drivetrain);
		this.xTargetInput = xTargetInput;
		this.yTargetInput = yTargetInput;
		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, drivetrain.getRightEncoder(), xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, drivetrain.getCenterEncoder(), yOutput);
		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
	}
	
	public void updateSettings() {
		xController = new PIDController(Variables.drivexPID_P, Variables.drivexPID_I, Variables.drivexPID_D, drivetrain.getRightEncoder(), xOutput);
		yController = new PIDController(Variables.driveyPID_P, Variables.driveyPID_I, Variables.driveyPID_D, drivetrain.getCenterEncoder(), yOutput);
		xController.setAbsoluteTolerance(0.1);
		yController.setAbsoluteTolerance(0.1);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		xController.setSetpoint(xTargetInput);
		yController.setSetpoint(yTargetInput);
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
