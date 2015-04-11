package com._2491nomythic.helios.commands.arm;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.settings.ControllerMap;

import edu.wpi.first.wpilibj.Timer;

/**
 * Sets the position of the arm during Tele-Op.
 */
public class ArmPositionSet extends CommandBase {
	
	private double armStickPos;
	private boolean hasBeenStopped;
//	private double target;
//	private double tolerance;
	private KeepArmFromFalling stopArm;
	private int reverse = -1;
	private Timer timer;
	
	/**
	 * Sets the position of the arm during Tele-Op.
	 */
	public ArmPositionSet() {
		requires(arm);
		stopArm = new KeepArmFromFalling(0.1);
		timer = new Timer();
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		reverse = -1;
		hasBeenStopped = false;
		timer.start();
		timer.reset();
//		tolerance = 0.1;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (arm.getHallEffectSensorValue()) {
			arm.resetEncoder();
		}
		armStickPos = oi.getAxisForDrive(ControllerMap.ArmController, ControllerMap.ArmAxis);
		double multiplier = 0.25;
		if (oi.getButton(ControllerMap.ArmController, ControllerMap.FasterArmButtonA)) {
			multiplier *= 2;
		}
		if (oi.getButton(ControllerMap.ArmController, ControllerMap.FasterArmButtonB)) {
			multiplier *= 2;
		}
		if (Math.abs(armStickPos) >= 0.05) {
			if (hasBeenStopped) {
				if (arm.getPosition() > 15) {
					reverse = -1;
				}
				else if(arm.getPosition() < -15) {
					reverse = 1;
				}
				stopArm.cancel();
				//replace with arm.set(0.0);? or just remove the cancel?
				
				
			}
			arm.set(-1 * armStickPos * multiplier * reverse);
			hasBeenStopped = false;
		}
		else if (!(hasBeenStopped)) {
			arm.stop();
	//		target = arm.getPosition();
			hasBeenStopped = true;
     		stopArm.start();
//     		timer.reset();
//			if (arm.getPosition() > 0) {
//				if (arm.get() == 0) {
//					if (arm.getPosition() > target + tolerance) {
//						arm.set((target - arm.getPosition()) / 10);
//     		if(timer.get() > 0.2) {
//				timer.reset();
//				timer.stop();
//				if(startPos == arm.getPosition() || (arm.getRate() > 0 != arm.get() > 0)) {
//					this.cancel(); //arm shouldn't be at same position- cancel
//								   //if our difference is negative, the motor input should also be negative
//								   //if our difference is positive the motor input should also be positive
//				}
//				
//			}
//					}
//				}
//				else {
//					if (arm.getPosition() < target) {
//						arm.stop();
//					}
//					else {
//						arm.set((target - arm.getPosition()) / 10);
//     		if(timer.get() > 0.2) {
//				timer.reset();
//				timer.stop();
//				if((arm.getRate() > 0 != arm.get() > 0)) {
//					this.cancel(); //arm shouldn't be at same position- cancel
//								   //if our difference is negative, the motor input should also be negative
//								   //if our difference is positive the motor input should also be positive
//				}
				
//			}
//					}
//				}
//			}
//			else {
//				if (arm.get() == 0) {
//					if (arm.getPosition() < target - tolerance) {
//						arm.set((target - arm.getPosition()) / 10);
//     		if(timer.get() > 0.2) {
//				timer.reset();
//				timer.stop();
//				if( (arm.getRate() > 0 != arm.get() > 0)) {
//					this.cancel(); //arm shouldn't be at same position- cancel
//								   
//				}
//				
//			}
//					}
//				}
//				else {
//					if (arm.getPosition() > target) {
//						arm.stop();
//					}
//					else {
//						arm.set((target - arm.getPosition()) / 10);
//     		if(timer.get() > 0.2) {
//				timer.reset();
//				if((arm.getRate() > 0 != arm.get() > 0)) {
//					this.cancel(); //arm shouldn't be at same position- cancel
//								   
//				}
				
//			}
//					}
//				}
//			}
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		arm.stop();
		stopArm.cancel();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
