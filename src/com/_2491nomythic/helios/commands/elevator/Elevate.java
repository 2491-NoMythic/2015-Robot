package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The main command that moves the elevator according to driver control during Tele-Op.
 */
public class Elevate extends CommandBase {
	
	private double elevatorStickPos;
	private boolean hasBeenStopped;
	
	public Elevate() {
		// Use requires() here to declare subsystem dependencies
		requires(elevator);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		elevatorStickPos = 0;//oi.getAxis(ControllerMap.codriverElevatorController, ControllerMap.CodriverElevatorAxis);
		if (0.05 >= Math.abs(elevatorStickPos) && !(hasBeenStopped)) {
			elevator.set(0);
			hasBeenStopped = true;
		}
		else if (Math.abs(elevatorStickPos) >= 0.05) {
			elevator.set(-1 * elevatorStickPos);
			hasBeenStopped = false;
			Variables.manualHasBeenUsed = true;
		}
		SmartDashboard.putNumber("Elevator Distance", elevator.getEncoder().getDistance());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		elevator.set(0.0);
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
