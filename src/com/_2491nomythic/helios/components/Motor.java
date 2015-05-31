package com._2491nomythic.helios.components;

/* A list of implemented functions and variables for the future JUnit Testing System
 * Depending on what our robot will be like next year, some of these will be deleted
 */

public interface Motor {
	
	 public void drive(double leftSpeed, double rightSpeed, double frontSpeeed, double backSpeed);
	 
	 public void drivePolar(double r, double theta, double rotation);
	 
	 public void driveCartesion(double x, double y, double rotation);
	 
	 default public void stop() {
		 drive(0, 0, 0, 0);
	 }
	 
	 public double getRightSpeed();
	 
	 public double getLeftSpeed();
	 
	 public double getFrontSpeed();
	 
	 public double getBackSpeed();
	 
}
