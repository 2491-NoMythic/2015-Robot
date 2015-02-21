package com._2491nomythic.helios;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.ReadDriverstation;
import com._2491nomythic.helios.commands.arm.ManuallyResetArmEncoder;
import com._2491nomythic.helios.commands.autonomous.DriveIntoAutoZone;
import com._2491nomythic.helios.commands.autonomous.PickupBinsFromStep;
import com._2491nomythic.helios.commands.drivetrain.AbsoluteDrive;
import com._2491nomythic.helios.commands.drivetrain.DrivePID;
import com._2491nomythic.helios.commands.drivetrain.DriveToTote;
import com._2491nomythic.helios.commands.drivetrain.FixGyroIssues;
import com._2491nomythic.helios.commands.drivetrain.ResetCenterEncoder;
import com._2491nomythic.helios.commands.drivetrain.ResetGyro;
import com._2491nomythic.helios.commands.drivetrain.ResetSideEncoders;
import com._2491nomythic.helios.commands.elevator.BottomElevator;
import com._2491nomythic.helios.commands.elevator.ManuallyResetElevatorEncoder;
import com._2491nomythic.helios.settings.Constants;
import com._2491nomythic.helios.subsystems.ExampleSubsystem;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	private Command autoCommand;
	private SendableChooser autoChooser;
	public static DrivePID drivePID;
	int session;
	Image frame;
	NIVision.Rect rect;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		CommandBase.init();
		// instantiate the command used for the autonomous period
		SmartDashboard.putData("Reset Gyro", new ResetGyro());
		SmartDashboard.putData("Rescan Gyro", new FixGyroIssues());
		SmartDashboard.putData("Read Driverstation Variables", new ReadDriverstation());
		SmartDashboard.putData("Absolute Drive", new AbsoluteDrive());
		autoChooser = new SendableChooser();
		autoChooser.addObject("Pick up Bins from Step", new PickupBinsFromStep());
		autoChooser.addDefault("Drive into Auto Zone", new DriveIntoAutoZone());
		SmartDashboard.putData("Autonomous", autoChooser);
		SmartDashboard.putData("Bottom out Elevator", new BottomElevator(-1.0));
		SmartDashboard.putData("Align with tote", new DriveToTote(0.25, 4.0));
		drivePID = new DrivePID(0.5, Constants.nullX, 1.0);
		SmartDashboard.putData("Test Drive PID", drivePID);
		SmartDashboard.putData("Reset Side Encoders", new ResetSideEncoders());
		SmartDashboard.putData("Reset Center Encoder", new ResetCenterEncoder());
		SmartDashboard.putData("Reset Arm Encoder", new ManuallyResetArmEncoder());
		SmartDashboard.putData("Reset Elevator Encoder", new ManuallyResetElevatorEncoder());
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit() {
		// schedule the autonomous command (example)
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoCommand != null) {
			autoCommand.cancel();
		}
		NIVision.IMAQdxStartAcquisition(session);
        rect = new NIVision.Rect(10, 10, 100, 100);
	}
	
	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
        NIVision.IMAQdxStopAcquisition(session);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        
        CameraServer.getInstance().setImage(frame);
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
