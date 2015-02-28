package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class BottomElevator extends CommandBase {
	double powerInput;
	double time = 4.0;
	Timer timer;
	boolean finished = false;
	
	public BottomElevator(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		requires(elevator);
		powerInput = -1.0 * Math.abs(power);
		timer = new Timer();
	}
	
	public BottomElevator(double power, double time) {
		requires(elevator);
		powerInput = -1.0 * Math.abs(power);
		timer = new Timer();
		this.time = time;
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
		if (timer.get() > time) {
			elevator.set(0.0);
			finished = true;
			System.out.println("Elevator bottom out timed out");
		}
		else if (elevator.getBottomSwitch()) {
			elevator.set(0.0);
			finished = true;
			System.out.println("Elevator hit switch");
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (finished);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Elevator bottom out finished");
		timer.stop();
		elevator.set(0.0);
		finished = false;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("Elevator bottom out interrupted");
		end();
	}
}
