package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;


/**
 * Drives the robot a specified distance in a specified direction with specified power.
 */
public class DriveDistance extends CommandBase {
	double powerInput;
	double xInput;
	double yInput;
	double xDrive;
	double yDrive;
	
	/**
	 * Drives the robot a specified distance in a specified direction with specified power.
	 * @param power The power of the drive motors.
	 * @param x The distance in the x direction to drive the robot.
	 * @param y The distance in the y direction to drive the robot.
	 */
	public DriveDistance(double power, double x, double y) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(drivetrain);
		powerInput = Math.abs(power);
		xInput = x;
		yInput = y;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		xDrive = powerInput;
		yDrive = powerInput;
		if (xInput < 0) {
			xDrive = (-1 * powerInput);
		}
		if (yInput < 0) {
			yDrive = (-1 * powerInput);
		}
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.drive(yDrive, yDrive, xDrive, xDrive);
		if ((yInput < 0 && drivetrain.getLeftEncoder().getPosition() <= yInput) || (yInput > 0 && drivetrain.getLeftEncoder().getPosition() >= yInput)) {
			yDrive = 0;
		}
		if ((xInput < 0 && drivetrain.getCenterEncoder().getPosition() <= xInput) || (xInput > 0 && drivetrain.getCenterEncoder().getPosition() >= xInput)) {
			xDrive = 0;
		}
	}
	
	// Mak this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return xDrive == 0 && yDrive == 0;
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
