package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 * Drives the robot for a specified amount of time in a specified direction with specified power.
 */
public class DriveTime extends CommandBase {
	double powerInput;
	double xInput;
	double yInput;
	double xDrive;
	double yDrive;
	Timer timer;
	
	/**
	 * 
	 * @param power The power of the drive motors.
	 * @param x The amount to drive the robot in the x direction.
	 * @param y The amount to drive the robot in the y direction.
	 */
	public DriveTime(double power, double x, double y) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(drivetrain);
		powerInput = Math.abs(power);
		xInput = x;
		yInput = y;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
		xDrive = powerInput;
		yDrive = powerInput;
		if (xInput < 0) {
			xDrive = (-1 * powerInput);
		}
		if (yInput < 0) {
			yDrive = (-1 * powerInput);
		}
		drivetrain.drive(yDrive, yDrive, xDrive, xDrive);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > Math.abs(xInput) && xDrive != 0) {
			xDrive = 0;
			drivetrain.drive(yDrive, yDrive, xDrive, xDrive);
		}
		if (timer.get() > Math.abs(yInput) && yDrive != 0) {
			yDrive = 0;
			drivetrain.drive(yDrive, yDrive, xDrive, xDrive);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return yDrive == 0 && xDrive == 0;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		drivetrain.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
