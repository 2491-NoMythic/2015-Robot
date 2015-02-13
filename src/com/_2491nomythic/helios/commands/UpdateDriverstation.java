package com._2491nomythic.helios.commands;

import com._2491nomythic.helios.settings.Variables;
import com._2491nomythic.util.FileData;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateDriverstation extends CommandBase {

	private Timer timer;
	private double nextRun;
	private FileData file;
	
	public UpdateDriverstation() {
		requires(extraSensors);
		timer = new Timer();
		file = new FileData("");
		SmartDashboard.putNumber("Gyro Sensitivity", Double.parseDouble(file.getWithDefault("GyroToDegrees", Double.toString(Variables.gyroToDegrees))));
	}
	
	protected void initialize() {
		timer.start();
		timer.reset();
		nextRun = timer.get();
	}

	protected void execute() {
		if (timer.get() > nextRun) {
			nextRun = nextRun + 0.25;
			updateStatus();
			readVariables();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		timer.stop();
	}

	protected void interrupted() {
		end();
	}
	
	private void updateStatus() {
		/*
		SmartDashboard.putNumber("Power Usage (watts)", extraSensors.getPowerDistributionPanel().getTotalPower());
		SmartDashboard.putNumber("Power Usage (amps)", extraSensors.getPowerDistributionPanel().getTotalCurrent());
		SmartDashboard.putNumber("Power Used (joules)", extraSensors.getPowerDistributionPanel().getTotalEnergy());
		*/
		SmartDashboard.putNumber("Current Elevator Target (totes)", Variables.elevatorTarget); 
		if (Variables.elevatorTarget < 0 || Variables.elevatorTarget >= Variables.toteHeight.length) {
			SmartDashboard.putString("Current Elevator Target (in)", "Manual Control");
		}
		else {
			SmartDashboard.putNumber("Current Elevator Target (in)", Variables.toteHeight[Variables.elevatorTarget]);
		}
		SmartDashboard.putNumber("Current Elevator Position", elevator.getEncoder().getDistance());
		SmartDashboard.putNumber("Gyro Positon", drivetrain.getGyro().getAngle());
	}
	
	private void readVariables() {
		double tmp;
		tmp = SmartDashboard.getNumber("Gyro Sensitivity");
		if (tmp != Variables.gyroToDegrees) {
			Variables.gyroToDegrees = tmp;
			file.set("GyroToDegrees", Double.toString(tmp));
		}
	}
	
}
