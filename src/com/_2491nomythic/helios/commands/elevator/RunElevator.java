package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.helios.settings.Variables;

/**
 * Runs the elevator based on Driver Control. Better. Hopefully.
 */
public class RunElevator extends CommandBase {
	
	private double target;
	
	/**
	 * Runs the elevator based on Driver Control. Better. Hopefully.
	 */
    public RunElevator() {
        requires(elevator);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int codriverPOV = oi.getController(ControllerMap.codriverElevatorController).getPOV();
    	if (oi.getButton(ControllerMap.driverElevatorController, ControllerMap.driverElevatorUp)) {
    		target = -1000;
    		elevator.set(Variables.elevatorMultiplier);
    	}
    	else if (oi.getButton(ControllerMap.driverElevatorController, ControllerMap.driverElevatorDown)) {
    		target = -1000;
    		elevator.set(-1.0 * Variables.elevatorMultiplier);
    		if (elevator.getBottomSwitch()) {
    			elevator.resetEncoder();
    		}
    	}
    	else if (codriverPOV == ControllerMap.codriverElevatorUpPOV[0] || codriverPOV == ControllerMap.codriverElevatorUpPOV[1] || codriverPOV == ControllerMap.codriverElevatorUpPOV[2]) {
    		target = -1000;
    		elevator.set(1.0 * Variables.elevatorMultiplier);
    	}
    	else if (codriverPOV == ControllerMap.codriverElevatorDownPOV[0] || codriverPOV == ControllerMap.codriverElevatorDownPOV[1] || codriverPOV == ControllerMap.codriverElevatorDownPOV[2]) {
    		target = -1000;
    		elevator.set(Variables.elevatorMultiplier);
    		if (elevator.getBottomSwitch()) {
    			elevator.resetEncoder();
    		}
    	}
    	else {
    		if (target < -500.0) {
    			elevator.set(0.0);
    			target = -400;
    		}
    		else if (target < -300) {
    			target = -200;
    		}
    		else if (target < -100) {
    			target = elevator.getPosition();
    		}
    		else {
	    		if (elevator.getPosition() < target) {
	    			double power = ((target - elevator.getPosition()) * 8);
	    			System.out.println("Elevator Power: " + power);
	    			elevator.setWithoutStop(power);
	    		}
	    		else {
	    			elevator.set(0.0);
	    		}
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
