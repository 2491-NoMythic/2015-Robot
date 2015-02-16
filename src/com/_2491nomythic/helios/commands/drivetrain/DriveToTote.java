package com._2491nomythic.helios.commands.drivetrain;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveToTote extends CommandBase {
	double power;
	double time;
	Timer timer;
	boolean finished = false;
	
	public DriveToTote(double power, double timeout) {
		requires(drivetrain);
		this.power = Math.abs(power);
		this.time = timeout;
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		finished = false;
		timer.start();
		timer.reset();
		drivetrain.drive(power, power, Constants.nullX, Constants.nullX);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() > time) {
			drivetrain.stop();;
			finished = true;
		}
		else if (elevator.getToteCheckLeft() && elevator.getToteCheckRight()) {
			drivetrain.stop();
			finished = true;
		}
		else if (elevator.getToteCheckLeft()) {
			drivetrain.drive(-0.5 * power, power, Constants.nullX, Constants.nullX);
		}
		else if (elevator.getToteCheckRight()) {
			drivetrain.drive(power, -0.5 * power, Constants.nullX, Constants.nullX);
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
