package com._2491nomythic.helios.commands;

import java.io.File;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.BinaryFileManager;

/**
 * Records the functions of the robot for playback using the PlayRobotScript command.
 */
public class RecordRobotScript extends CommandBase {
	private BinaryFileManager file;
	float floatArray[] = new float[7];
	
	/**
	 * Records the functions of the robot for playback using the PlayRobotScript command.
	 * @param filename The name of the file to record to.
	 */
	public RecordRobotScript(String filename) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		File directory = new File(Constants.homeDirectory + "Scripts");
		if(!directory.exists()) {
			directory.mkdir();
		}
		file = new BinaryFileManager(Constants.homeDirectory + "Scripts/" + filename + Constants.recordedFileExtension);
		
		
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		file.clear();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		floatArray[0] = (float) drivetrain.getCurrentLeftSpeed();
		floatArray[1] = (float) drivetrain.getCurrentFrontSpeed();
		floatArray[2] = (float) drivetrain.getCurrentBackSpeed();
		floatArray[3] = (float) drivetrain.getCurrentRightSpeed();
		floatArray[4] = (float) elevator.get();
		floatArray[5] = (float) arm.get();
		floatArray[6] = (float) grabber.get();
		
		file.append(floatArray);
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
