package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class BottomElevator extends CommandBase {
	double powerInput;
	double time = 4.0;
	Timer timer;
	boolean finished;
	
	public BottomElevator(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(elevator);
		powerInput = -1.0 * Math.abs(power);
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		timer.reset();
		elevator.set(powerInput);
		finished = false;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > Math.abs(time)) {
			elevator.set(0.0);
			finished = true;
			SmartDashboard.putString("Bottom Elevator", "Bottom Out Timed Out");
		}
		else if (elevator.getBottomSwitch()) {
			elevator.set(0.0);
			finished = true;
			SmartDashboard.putString("Bottom Elevator", "Hit Switch");
		}
	}
	
	// Mak this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (finished);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		elevator.set(0.0);
		finished = false;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
