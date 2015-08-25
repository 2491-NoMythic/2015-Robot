package com._2491nomythic.util.components;

/* 
 * A list of implemented functions for the future JUnit Testing System
 * Depending on what our robot will be like next year, some of these will be deleted
 */

/**
 * This code has been taken from a previous program and adapted to suit the needs of FRC Team 2491.
 * The original code was devised by FRC Team 4931. The team's entire program can be accessed through their 
 * web site (http://www.evilletech.com).
 * @author Original Author (Team 4931): Zach Anderson
 * @author Adapting Author (Team 2491): Dario Connor Alvarez
 */

public interface Motor {
	public void setSpeed(double speed);
	
	public double getSpeed();
	
	default public void stop() {
		setSpeed(0.0);
	}
}
