package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;

/**
 * Acts as a brake for the drivetrain.
 */
public class StopRobot extends CommandBase {
	private double tolerance;
	private double targetY;
	
	/**
	 * Acts as a brake for the drivetrain.
	 * @param tolerance distance allowed before the brake engages.
	 */
    public StopRobot(double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetY = drivetrain.getRightEncoder().getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(drivetrain.getFrontRightMotor().getSpeed() == 0) {
    		if(drivetrain.getRightEncoder().getDistance() > targetY + tolerance) {
    			drivetrain.driveRight(targetY - drivetrain.getRightEncoder().getDistance() / 10);
    			drivetrain.driveLeft(targetY - drivetrain.getRightEncoder().getDistance() / 10);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
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
