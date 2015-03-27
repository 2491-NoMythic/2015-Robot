package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;
import com._2491nomythic.helios.settings.Variables;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Runs the elevator based on Driver Control. Better. Hopefully.
 */
public class RunElevator extends CommandBase {
	
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
    		elevator.set(Variables.elevatorMultiplier);
    	}
    	else if (oi.getButton(ControllerMap.driverElevatorController, ControllerMap.driverElevatorDown)) {
    		elevator.set(-1.0 * Variables.elevatorMultiplier);
    	}
    	else if (codriverPOV == ControllerMap.codriverElevatorUpPOV[0] || codriverPOV == ControllerMap.codriverElevatorUpPOV[1] || codriverPOV == ControllerMap.codriverElevatorUpPOV[2]) {
    		elevator.set(Variables.elevatorMultiplier);
    	}
    	else if (codriverPOV == ControllerMap.codriverElevatorDownPOV[0] || codriverPOV == ControllerMap.codriverElevatorDownPOV[1] || codriverPOV == ControllerMap.codriverElevatorDownPOV[2]) {
    		elevator.set(-1.0 * Variables.elevatorMultiplier);
    	}
    	else {
    		elevator.set(0.0);
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
