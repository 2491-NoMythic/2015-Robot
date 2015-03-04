package com._2491nomythic.helios.commands;

import com._2491nomythic.helios.Robot;
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
		file = new FileData("/home/lvuser/SmartDashboard.txt");
		SmartDashboard.putNumber("Gyro Sensitivity", file.getDoubleWithDefault("GyroToDegrees", Variables.gyroToDegrees));
		SmartDashboard.putNumber("Drive Y PID P", file.getDoubleWithDefault("DriveYPIDP", Variables.driveyPID_P));
		SmartDashboard.putNumber("Drive Y PID I", file.getDoubleWithDefault("DriveYPIDI", Variables.driveyPID_I));
		SmartDashboard.putNumber("Drive Y PID D", file.getDoubleWithDefault("DriveYPIDD", Variables.driveyPID_D));
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
		 * SmartDashboard.putNumber("Power Usage (watts)",
		 * extraSensors.getPowerDistributionPanel().getTotalPower());
		 * SmartDashboard.putNumber("Power Usage (amps)",
		 * extraSensors.getPowerDistributionPanel().getTotalCurrent());
		 * SmartDashboard.putNumber("Power Used (joules)",
		 * extraSensors.getPowerDistributionPanel().getTotalEnergy());
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
		SmartDashboard.putNumber("Right Encoder", drivetrain.getRightEncoder().getDistance());
		SmartDashboard.putNumber("Center Encoder", drivetrain.getCenterEncoder().getDistance());
		SmartDashboard.putBoolean("Top limit switch", elevator.getTopSwitch());
		SmartDashboard.putBoolean("Bottom limit switch", elevator.getBottomSwitch());
		SmartDashboard.putNumber("Arm Encoder", arm.getPosition());
		SmartDashboard.putNumber("Arm Power", arm.get());
	}
	
	private void readVariables() {
		double tmp;
		tmp = SmartDashboard.getNumber("Gyro Sensitivity");
		if (tmp != Variables.gyroToDegrees) {
			Variables.gyroToDegrees = tmp;
			file.set("GyroToDegrees", Double.toString(tmp));
			drivetrain.getGyro().setSensitivity(tmp);
		}
		
		tmp = SmartDashboard.getNumber("Drive Y PID P");
		if (tmp != Variables.driveyPID_P) {
			Variables.driveyPID_P = tmp;
			file.set("DriveYPIDP", Double.toString(tmp));
			Robot.drivePID.updateSettings();
		}
			
		tmp = SmartDashboard.getNumber("Drive Y PID I");
		if (tmp != Variables.driveyPID_I) {
			Variables.driveyPID_I = tmp;
			file.set("DriveYPIDI", Double.toString(tmp));
			Robot.drivePID.updateSettings();
		}
	
		tmp = SmartDashboard.getNumber("Drive Y PID D");
		if (tmp != Variables.driveyPID_D) {
			Variables.driveyPID_D = tmp;
			file.set("DriveYPIDD", Double.toString(tmp));
			Robot.drivePID.updateSettings();
		}
	
	}
}
