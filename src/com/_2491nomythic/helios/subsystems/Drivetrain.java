package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.util.CartesianCoord;
import com._2491nomythic.util.PolarCoord;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	CANTalon frontRight, frontCenter, frontLeft, backRight, backCenter, backLeft;
	Encoder encoderLeft, encoderCenter, encoderRight;
	Gyro gyro;
	private static Drivetrain instance;
	
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
	
	private Drivetrain() {
		frontLeft = new CANTalon(Constants.driveTalonFrontLeftChannel);
		frontCenter = new CANTalon(Constants.driveTalonFrontCenterChannel);
		frontRight = new CANTalon(Constants.driveTalonFrontRightChannel);
		backLeft = new CANTalon(Constants.driveTalonBackLeftChannel);
		backCenter = new CANTalon(Constants.driveTalonBackCenterChannel);
		backRight = new CANTalon(Constants.driveTalonBackRightChannel);
		
		encoderLeft = new Encoder(Constants.driveEncoderLeftAChannel, Constants.driveEncoderLeftBChannel, Constants.driveEncoderLeftReversed, CounterBase.EncodingType.k1X);
		encoderCenter = new Encoder(Constants.driveEncoderCenterAChannel, Constants.driveEncoderCenterBChannel, Constants.driveEncoderCenterReversed, CounterBase.EncodingType.k1X);
		encoderRight = new Encoder(Constants.driveEncoderRightAChannel, Constants.driveEncoderRightBChannel, Constants.driveEncoderRightReversed, CounterBase.EncodingType.k1X);
		encoderLeft.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderCenter.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderRight.setDistancePerPulse(Constants.driveEncoderToFeet);
		encoderLeft.reset();
		encoderCenter.reset();
		encoderRight.reset();
	}
	
	
	
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	public void drive(double leftSpeed, double rightSpeed, double frontSpeed, double backSpeed){
		frontLeft.set(leftSpeed);
		frontCenter.set(frontSpeed);
		frontRight.set(-1.0 * rightSpeed);
		backLeft.set(leftSpeed);
		backCenter.set(-1.0 * backSpeed);
		backRight.set(-1.0 * rightSpeed);
	}
	
	public void driveCartesian(double x, double y, double rotation) {
		double left = y + rotation;
		double right = y - rotation;
		
		if(left > 1){left = 1;}
		if(left < -1){left = -1;}
		if(right > 1){right = 1;}
		if(right < -1){right = -1;}
		
		drive(left, right, x, x);
	}
	
	public void driveCartesian(CartesianCoord coords, double rotation) {
		driveCartesian(coords.getX(), coords.getY(), rotation);
	}
	
	public void drivePolar(PolarCoord coords, double rotation) {
		driveCartesian(coords.getCartesian(), rotation);
	}
	
	public void drivePolar(double r, double theta, double rotation) {
		PolarCoord polarCoords = new PolarCoord(r, theta);
		drivePolar(polarCoords, rotation);
	}
	
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	public Encoder getCenterEncoder() {
		return encoderCenter;
	}
	
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	public void stop() {
		
		drive(0.0,0.0,0.0,0.0);
			
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
}

