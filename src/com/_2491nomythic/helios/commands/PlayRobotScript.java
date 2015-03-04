package com._2491nomythic.helios.commands;

import java.nio.ByteBuffer;

import com._2491nomythic.util.BinaryFileManager;

/**
 *
 */
public class PlayRobotScript extends CommandBase {
	
	private ByteBuffer buffer;
	private String path;
	
	public PlayRobotScript(String path) {
		requires(drivetrain);
		requires(elevator);
		requires(arm);
		requires(grabber);
		this.path = path;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		BinaryFileManager file = new BinaryFileManager(path);
		buffer = ByteBuffer.wrap(file.read());
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drivetrain.driveLeft(buffer.getFloat());
		drivetrain.driveCenter(buffer.getFloat(), buffer.getFloat());
		drivetrain.driveRight(buffer.getFloat());
		elevator.set(buffer.getFloat());
		arm.set(buffer.getFloat());
		grabber.set(buffer.getFloat());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !buffer.hasRemaining();
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}