package com._2491nomythic.helios.commands;


import java.util.regex.Pattern;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.TextFileManager;


/**
 *
 */
public class TextPlayRobotScript extends CommandBase {
	private String path;
	TextFileManager file;
	private String[] data;
	private static Pattern pattern = Pattern.compile(", ");
	private boolean isDone = false;
	String myString;
	
    public TextPlayRobotScript(String filename) {
    	requires(drivetrain);
		requires(elevator);
		requires(arm);
		requires(grabber);
		this.path = Constants.homeDirectory + "Scripts/" + filename + ".csv";
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	file = new TextFileManager(path);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	myString = file.readLine();
    	if(myString == null) {
    		isDone = true;
    	}
    	else {
	    	data = pattern.split(myString);
	    	if(data.length == 7) {
	    		drivetrain.driveLeft(Double.parseDouble(data[0]));
	    		drivetrain.driveCenter(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
	    		drivetrain.driveRight(Double.parseDouble(data[3]));
	    		elevator.set(Double.parseDouble(data[4]));
	    		arm.set(Double.parseDouble(data[5]));
	    		grabber.set(Double.parseDouble(data[6]));
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
