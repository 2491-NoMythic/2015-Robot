package com._2491nomythic.helios.commands;

import java.io.File;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.TextFileManager;

/**
 * Records a script to play with TextPlayRobotScript.
 */
public class TextRecordRobotScript extends CommandBase {
	private TextFileManager file;;
	
	/**
	 * Records a script to play with TextPlayRobotScript.
	 * @param filename The name of the file to save the script to.
	 */
    public TextRecordRobotScript(String filename) {
    	File directory = new File(Constants.homeDirectory + "Scripts");
    	if(!directory.exists()) {
    		directory.mkdir();
    	}
        file = new TextFileManager(Constants.homeDirectory + "Scripts/" + filename + ".csv");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	file.clear();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	file.appendLine(String.valueOf(drivetrain.getCurrentLeftSpeed()) + ", " + String.valueOf(drivetrain.getCurrentFrontSpeed()) + ", " + String.valueOf(drivetrain.getCurrentBackSpeed()) + ", " + String.valueOf(drivetrain.getCurrentRightSpeed()) + ", " + String.valueOf(elevator.get()) + ", " + String.valueOf(arm.get()) + ", " + String.valueOf(grabber.get()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	file.closeFileScanner();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
