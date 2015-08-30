package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.commands.drivetrain.Drive;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.CartesianCoord;
import com._2491nomythic.util.PolarCoord;
import com._2491nomythic.util.components.interfaces.Gyroscope;
import com._2491nomythic.util.components.interfaces.Motor;
import com._2491nomythic.util.components.interfaces.Encoder;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The thing we drive with.
 */
public class Drivetrain extends Subsystem {
	Motor frontRight, frontCenter, frontLeft, backRight, backCenter, backLeft;
	Encoder encoderLeft, encoderCenter, encoderRight;
	Gyroscope gyro;
	double currentLeftSpeed, currentRightSpeed, currentBackSpeed, currentFrontSpeed;
	
	/**
	 * The thing we drive with.
	 */
	public Drivetrain(Motor frontLeftMotor, Motor frontCenterMotor, Motor frontRightMotor, Motor backLeftMotor, Motor backCenterMotor, Motor backRightMotor, Encoder encoderLeft, Encoder encoderCenter, Encoder encoderRight, Gyroscope gyro) {

		this.frontLeft = frontLeftMotor;
		this.frontCenter = frontCenterMotor;
		this.frontRight = frontRightMotor;
		this.backLeft = backLeftMotor;
		this.backCenter = backCenterMotor;
		this.backRight = backRightMotor;
		
		
		/*
		frontLeft = new CANTalon(Constants.driveTalonFrontLeftChannel);
		frontCenter = new CANTalon(Constants.driveTalonFrontCenterChannel);
		frontRight = new CANTalon(Constants.driveTalonFrontRightChannel);
		backLeft = new CANTalon(Constants.driveTalonBackLeftChannel);
		backCenter = new CANTalon(Constants.driveTalonBackCenterChannel);
		backRight = new CANTalon(Constants.driveTalonBackRightChannel);
		*/
		
		this.encoderLeft = encoderLeft;
		this.encoderCenter = encoderCenter;
		this.encoderRight = encoderRight;
		
		/*
		encoderLeft = new Encoder(Constants.driveEncoderLeftAChannel, Constants.driveEncoderLeftBChannel, Constants.driveEncoderLeftReversed, CounterBase.EncodingType.k1X);
		encoderCenter = new Encoder(Constants.driveEncoderCenterAChannel, Constants.driveEncoderCenterBChannel, Constants.driveEncoderCenterReversed, CounterBase.EncodingType.k1X);
		encoderRight = new Encoder(Constants.driveEncoderRightAChannel, Constants.driveEncoderRightBChannel, Constants.driveEncoderRightReversed, CounterBase.EncodingType.k1X);
		encoderLeft.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderCenter.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderRight.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderLeft.reset();
		encoderCenter.reset();
		encoderRight.reset();
		*/
		
		this.gyro = gyro;
		
		/*
		gyro = new Gyro(Constants.gyroChannel);
		gyro.initGyro();
		gyro.setSensitivity(Variables.gyroToDegrees);
		*/
	}
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	/**
	 * Sets the power of the drive motors.
	 * @param leftSpeed The power of the left motors.
	 * @param rightSpeed The power of the right motors.
	 * @param frontSpeed The power of the front motor.
	 * @param backSpeed The power of the back motor.
	 */
	public void drive(double leftSpeed, double rightSpeed, double frontSpeed, double backSpeed) {
		driveLeft(leftSpeed);
		driveRight(rightSpeed);
		driveCenter(frontSpeed, backSpeed);
	}
	
	/**
	 * Sets the power of the right drive motors.
	 * @param speed The power of the right drive motors.
	 */
	public void driveRight(double speed) {
		frontRight.setSpeed(-1.0 * speed);
		backRight.setSpeed(-1.0 * speed);
		currentRightSpeed = speed;
	}
	
	/**
	 * Sets the power of the left drive motors.
	 * @param speed The power of the left drive motors.
	 */
	public void driveLeft(double speed) {
		frontLeft.setSpeed(speed);
		backLeft.setSpeed(speed);
		currentLeftSpeed = speed;
	}
	
	/**
	 * Sets the power of the center drive motors.
	 * @param frontSpeed The power of the front center motor.
	 * @param backSpeed The power of the back center motor.
	 */
	public void driveCenter(double frontSpeed, double backSpeed) {
		frontCenter.setSpeed(frontSpeed);
		backCenter.setSpeed(-1.0 * backSpeed);
		currentFrontSpeed = frontSpeed;
		currentBackSpeed = backSpeed;
	}
	
	/**
	 * Sets the power of the drive motors using Cartesian coordinates.
	 * @param x The power of the center drive motors.
	 * @param y The power of the center drive motors.
	 * @param rotation The amount that the robot turns.
	 */
	public void driveCartesian(double x, double y, double rotation) {
		double left = y + rotation;
		double right = y - rotation;
		
		x *= Constants.centerMotorMultiplier;
		
		if (left > 1) {
			left = 1;
		}
		if (left < -1) {
			left = -1;
		}
		if (right > 1) {
			right = 1;
		}
		if (right < -1) {
			right = -1;
		}
		
		drive(left, right, x, x);
	}
	
	/**
	 * Sets the drive motors using a set of coords created with the CartesianCoord class.
	 * @param coords The motors' powers put into a CartesianCoord.
	 * @param rotation The amount that the robot turns.
	 */
	public void driveCartesian(CartesianCoord coords, double rotation) {
		driveCartesian(coords.getX(), coords.getY(), rotation);
	}
	
	/**
	 * Sets the drive motors using a set of coords created with the PolarCoord class.
	 * @param coords The motors' powers put into a PolarCoord.
	 * @param rotation The amount that the robot turns.
	 */
	public void drivePolar(PolarCoord coords, double rotation) {
		driveCartesian(coords.getCartesian(), rotation);
	}
	
	/**
	 * Sets the drive power of the drive motors using polar coordinates.
	 * @param r The power of the motors in the direction theta.
	 * @param theta The direction the robot drives in degrees.
	 * @param rotation The amount that the robot turns.
	 */
	public void drivePolar(double r, double theta, double rotation) {
		PolarCoord polarCoords = new PolarCoord(r, theta);
		drivePolar(polarCoords, rotation);
	}
	
	/**
	 * Sets the drive motors such that forward is always the same direction, even when you rotate.
	 * @param coords The PolarCoord that contains coords for the drive motors.
	 * @param rotation The amount that the robot turns.
	 */
	public void driveAbsolute(PolarCoord coords, double rotation) {
		coords.setTheta(coords.getTheta() + gyro.getAngle() * Math.PI / 180);
		drivePolar(coords, rotation);
	}
	
	/**
	 * Sets the drive motors such that forward is always the same direction, even when you rotate.
	 * @param r The power of the motors in the direction theta.
	 * @param theta The direction the robot drives in degrees.
	 * @param rotation The amount that the robot turns.
	 */
	public void driveAbsolute(double r, double theta, double rotation) {
		driveAbsolute(new PolarCoord(r, theta), rotation);
	}
	
	/**
	 * 
	 * @return The value of the gyro.
	 */
	public Gyroscope getGyro() {
		return gyro;
	}
	
	/**
	 * 
	 * @return The value of the left drive encoder.
	 */
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	/**
	 * 
	 * @return The value of the center drive encoder.
	 */
	public Encoder getCenterEncoder() {
		return encoderCenter;
	}
	
	/**
	 * 
	 * @return The value of the right drive encoder.
	 */
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	/**
	 * 
	 * @return The front left drive motor.
	 */
	public Motor getFrontLeftMotor() {
		return frontLeft;
	}
	
	/**
	 * 
	 * @return The back left drive motor.
	 */
	public Motor getBackLeftMotor() {
		return backLeft;
	}
	
	/**
	 * 
	 * @return The front right drive motor.
	 */
	public Motor getFrontRightMotor() {
		return frontRight;
	}
	
	/**
	 * 
	 * @return The back right drive motor.
	 */
	public Motor getBackRightMotor() {
		return backRight;
	}
	
	/**
	 * 
	 * @return The front center drive motor.
	 */
	public Motor getFrontCenterMotor() {
		return frontCenter;
	}
	
	/**
	 * 
	 * @return The back center drive motor.
	 */
	public Motor getBackCenterMotor() {
		return backCenter;
	}
	
	/**
	 * 
	 * @return The current power being applied to the right drive motors.
	 */
	public double getCurrentRightSpeed() {
		return currentRightSpeed;
	}
	
	/**
	 * 
	 * @return The current power being applied to the left drive motors.
	 */
	public double getCurrentLeftSpeed() {
		return currentLeftSpeed;
	}
	
	/**
	 * 
	 * @return The current power being applied to the front center drive motor.
	 */
	public double getCurrentFrontSpeed() {
		return currentFrontSpeed;
	}
	
	/**
	 * 
	 * @return The currrent power being applied to the back center drive motor.
	 */
	public double getCurrentBackSpeed() {
		return currentBackSpeed;
	}
	
	/**
	 * Stops the drivetrain.
	 */
	public void stop() {
		drive(0.0, 0.0, 0.0, 0.0);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	
}
