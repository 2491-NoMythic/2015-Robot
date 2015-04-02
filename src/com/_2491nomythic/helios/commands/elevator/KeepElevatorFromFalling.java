package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;

public class KeepElevatorFromFalling extends CommandBase {
	
	private double target;
	
	/**
	 * Keeps the elevator from slipping downwards due to bin weight
	 * WARNING!!! This command doesn't requires(elevator), so it needs to be canceled manually so it doesn't constantly set the elevator to 0;
	 */
	public KeepElevatorFromFalling() {
		// We don't requires(elevator) so this command can be run by another command that requires(elevator) without canceling it.
	}

	protected void initialize() {
		target = elevator.getPosition();
	}

	protected void execute() {
		if (elevator.getPosition() < target) {
			double power = (target - elevator.getPosition() * 8);
			System.out.println("Elevator Power: " + power);
			elevator.set(power);
		}
		else {
			elevator.stop();
		}
	}

	protected boolean isFinished() {
		return false; // Never finished
	}

	protected void end() {
		elevator.stop();
	}

	protected void interrupted() {
		end();		
	}
	
}
