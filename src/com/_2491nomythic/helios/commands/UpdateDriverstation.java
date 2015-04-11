package com._2491nomythic.helios.commands;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.settings.Variables;
import com._2491nomythic.util.FileData;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Puts some stuff on the DriverStation and collects some stuff from the DriverStation.
 */
public class UpdateDriverstation extends CommandBase {
	
	private Timer timer;
	private double nextRun;
	private FileData file;
	private double[] maxValues = new double[8];
	private boolean maxValuesEnabled = false;
	
	/**
	 * Puts some stuff on the DriverStation and collects some stuff from the DriverStation.
	 */
	public UpdateDriverstation() {
		requires(extraSensors);
		timer = new Timer();
		file = new FileData(Constants.homeDirectory + "SmartDashboard.txt");
		setRunWhenDisabled(true);
		SmartDashboard.putNumber("Gyro Sensitivity", file.getDoubleWithDefault("GyroToDegrees", Variables.gyroToDegrees));
		SmartDashboard.putNumber("Elevator Speed", 1.0);
		SmartDashboard.putBoolean("Show Max Power Usage", false);
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
	
	/**
	 * Updates the values on the SmartDashboard if maxValuesEnabled is changed.
	 */
	private void updateStatus() {
		
		if (SmartDashboard.getBoolean("Show Max Power Usage")) {
			if (!maxValuesEnabled) {
				maxValues = new double[8];
				maxValuesEnabled = true;
			}
			SmartDashboard.putNumber("Front Left Power Usage", updateMaxValue(0, extraSensors.getPowerDistributionPanel().getCurrent(13)));
			SmartDashboard.putNumber("Front Center Power Usage", updateMaxValue(1, extraSensors.getPowerDistributionPanel().getCurrent(1)));
			SmartDashboard.putNumber("Front Right Power Usage", updateMaxValue(2, extraSensors.getPowerDistributionPanel().getCurrent(2)));
			SmartDashboard.putNumber("Back Left Power Usage", updateMaxValue(3, extraSensors.getPowerDistributionPanel().getCurrent(15)));
			SmartDashboard.putNumber("Back Center Power Usage", updateMaxValue(4, extraSensors.getPowerDistributionPanel().getCurrent(14)));
			SmartDashboard.putNumber("Back Right Power Usage", updateMaxValue(5, extraSensors.getPowerDistributionPanel().getCurrent(0)));
			SmartDashboard.putNumber("Arm Power Usage", updateMaxValue(6, extraSensors.getPowerDistributionPanel().getCurrent(3)));
			SmartDashboard.putNumber("Hook Power Usage", updateMaxValue(7, extraSensors.getPowerDistributionPanel().getCurrent(4)));
		}
		else {
			if (maxValuesEnabled) {
				maxValuesEnabled = false;
			}
			SmartDashboard.putNumber("Front Left Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(13));
			SmartDashboard.putNumber("Front Center Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(1));
			SmartDashboard.putNumber("Front Right Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(2));
			SmartDashboard.putNumber("Back Left Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(15));
			SmartDashboard.putNumber("Back Center Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(14));
			SmartDashboard.putNumber("Back Right Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(0));
			SmartDashboard.putNumber("Arm Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(3));
			SmartDashboard.putNumber("Hook Power Usage", extraSensors.getPowerDistributionPanel().getCurrent(4));
		}
		
//		SmartDashboard.putNumber("Current Elevator Target (totes)", Variables.elevatorTarget);
//		if (Variables.elevatorTarget < 0 || Variables.elevatorTarget >= Variables.toteHeight.length) {
//			SmartDashboard.putString("Current Elevator Target (in)", "Manual Control");
//		}
//		else {
//			SmartDashboard.putNumber("Current Elevator Target (in)", Variables.toteHeight[Variables.elevatorTarget]);
//		}
//		SmartDashboard.putNumber("Current Elevator Position", elevator.getEncoder().getDistance());
		SmartDashboard.putNumber("Gyro Positon", drivetrain.getGyro().getAngle());
		SmartDashboard.putNumber("Right Encoder", drivetrain.getRightEncoder().getDistance());
		SmartDashboard.putNumber("Center Encoder", drivetrain.getCenterEncoder().getDistance());
		SmartDashboard.putBoolean("Top limit switch", elevator.getTopSwitch());
		SmartDashboard.putBoolean("Bottom limit switch", elevator.getBottomSwitch());
		SmartDashboard.putNumber("Arm Encoder", arm.getPosition());
		SmartDashboard.putNumber("Elevator Encoder", elevator.getPosition());
	}
	
	/**
	 * Sends the values on the SmartDashboard to the variables file.
	 */
	private void readVariables() {
		double tmp;
		tmp = SmartDashboard.getNumber("Gyro Sensitivity");
		if (tmp != Variables.gyroToDegrees) {
			Variables.gyroToDegrees = tmp;
			file.set("GyroToDegrees", Double.toString(tmp));
			drivetrain.getGyro().setSensitivity(tmp);
		}
		
		Variables.elevatorMultiplier = SmartDashboard.getNumber("Elevator Speed");
	}
	
	/**
	 * Updates the max value to be higher if the current value is exceeded.
	 * @param pos The value to check the max for.
	 * @param value The value to update the max with if it is higher than the current max.
	 * @return The (possibly new) max value.
	 */
	private double updateMaxValue (int pos, double value) {
		if(maxValues[pos] < value) {
			maxValues[pos] = value;
		}
		return maxValues[pos];
	}
}
