package com._2491nomythic.helios.commands.elevator;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.Variables;

/**
 * Sets the height of the elevator based on the height of totes.
 */
public class SetToteHeight extends CommandBase {
	private int target;
	private boolean lip;
	private boolean onPlatform;
	private static GoToToteHeight go = new GoToToteHeight();
	
	/**
	 * 
	 * @param totes The number of totes to put the elevator at.
	 * @param underLip Whether or not to put the elevator under the lip of the top tote.
	 * @param platform Whether or not to add the scoring platform's height to the elevator's height.
	 */
	public SetToteHeight(int totes, boolean underLip, boolean platform) {
		target = totes;
		lip = underLip;
		onPlatform = platform;
	}
	
	/**
	 * 
	 * @param totes The number of totes to put the elevator at.
	 * @param underLip Whether or not to put the elevator under the lip of the top tote.
	 */
    public SetToteHeight(int totes, boolean underLip) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	target = totes;
    	lip = underLip;
    	onPlatform = false;
    }
    
    /**
     * 
     * @param totes The number of totes to put the elevator at.
     */
    public SetToteHeight(int totes) {
    	target = totes;
    	lip = false;
    	onPlatform = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Variables.elevatorTarget = target;
    	Variables.underLipStatus = lip;
    	Variables.platformStatus = onPlatform;
    	
    	go.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
