package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends CommandBase {
double powerInput;
double xInput;
double yInput;

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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double xDrive = powerInput;
    	double yDrive = powerInput;
    	if(xInput < 0) {
    		xDrive = (-1 * powerInput);
    	}
    	if(yInput < 0) {
    		yDrive = (-1 * powerInput);
    	}
    	drivetrain.drive(yDrive, yDrive, xDrive, xDrive);
    	if(drivetrain.getLeftEncoder().get() == yInput) {
    		drivetrain.drive(0, 0, xDrive, xDrive);
    	}
    	if(drivetrain.getCenterEncoder().get() == xInput) {
    		drivetrain.drive(yDrive, yDrive, 0, 0);
    	}
    	
    	
    }

    // Mak this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
