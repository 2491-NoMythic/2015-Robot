package com._2491nomythic.helios.commands.arm;
import com._2491nomythic.helios.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
/**
 *
 */
public class RunArmTime extends CommandBase {
		private double time;
		private double power;
		private Timer timer;
    public RunArmTime(double power, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    	this.time = time;
    	this.power = power;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    	arm.set(power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > time) {
        	arm.stop();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.stop();
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
