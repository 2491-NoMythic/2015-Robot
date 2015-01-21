package com._2491nomythic.helios.subsystems;

import com._2491nomythic.helios.settings.Constants;

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
	Drivetrain instance;
	
	public Drivetrain getInstance() {
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
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

