package com._2491nomythic.helios;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com._2491nomythic.helios.commands.CommandBase;
import com._2491nomythic.helios.commands.TextPlayRobotScript;
import com._2491nomythic.helios.commands.TextRecordRobotScript;
import com._2491nomythic.helios.commands.arm.ManuallyResetArmEncoder;
import com._2491nomythic.helios.commands.arm.RunArmWithPID;
import com._2491nomythic.helios.commands.autonomous.DoNothing;
import com._2491nomythic.helios.commands.autonomous.DriveIntoAutoZone;
import com._2491nomythic.helios.commands.autonomous.DriveIntoAutoZoneAndDrop;
import com._2491nomythic.helios.commands.autonomous.OneToteAndOneBin;
import com._2491nomythic.helios.commands.autonomous.PickupBinFromStep;
import com._2491nomythic.helios.commands.autonomous.PickupBinsFromStep;
import com._2491nomythic.helios.commands.autonomous.TwoTotesAndOneBin;
import com._2491nomythic.helios.commands.drivetrain.AbsoluteDrive;
import com._2491nomythic.helios.commands.drivetrain.FixGyroIssues;
import com._2491nomythic.helios.commands.drivetrain.ResetCenterEncoder;
import com._2491nomythic.helios.commands.drivetrain.ResetGyro;
import com._2491nomythic.helios.commands.drivetrain.ResetSideEncoders;
import com._2491nomythic.helios.commands.elevator.BottomElevator;
import com._2491nomythic.helios.commands.elevator.ElevatePID;
import com._2491nomythic.helios.commands.elevator.KeepElevatorFromFalling;
import com._2491nomythic.helios.commands.elevator.ManuallyResetElevatorEncoder;
import com._2491nomythic.helios.subsystems.ExampleSubsystem;

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
		SmartDashboard.putData("Absolute Drive", new AbsoluteDrive());
		autoChooser = new SendableChooser();
		autoChooser.addObject("Pick up bin from step", new PickupBinFromStep());
		autoChooser.addObject("Pick up two bins from step", new PickupBinsFromStep());
		autoChooser.addDefault("Grab two totes and a bin", new TwoTotesAndOneBin(false));
		autoChooser.addObject("Grab one tote and a bin from center", new OneToteAndOneBin(true));
		autoChooser.addObject("Grab one tote and a bin from left", new OneToteAndOneBin(false));
		autoChooser.addObject("Drive into auto zone", new DriveIntoAutoZone());
		autoChooser.addObject("Drive into auto zone and drop item", new DriveIntoAutoZoneAndDrop());
		autoChooser.addObject("Do Nothing", new DoNothing());
		SmartDashboard.putData("Autonomous", autoChooser);
		SmartDashboard.putData("Bottom out Elevator", new BottomElevator(-1.0));
		SmartDashboard.putData("Reset Side Encoders", new ResetSideEncoders());
		SmartDashboard.putData("Reset Center Encoder", new ResetCenterEncoder());
		SmartDashboard.putData("Reset Arm Encoder", new ManuallyResetArmEncoder());
		SmartDashboard.putData("Reset Elevator Encoder", new ManuallyResetElevatorEncoder());
		SmartDashboard.putData("Move arm to center", new RunArmWithPID(0.0));
		SmartDashboard.putData("Record Driver Joystick Inputs", new TextRecordRobotScript("TestScript"));
		SmartDashboard.putData("Play Driver Joystick Inputs", new TextPlayRobotScript("TestScript"));
		SmartDashboard.putData("Hold Elevator", new KeepElevatorFromFalling());
		SmartDashboard.putData("Elevator PID Test", new ElevatePID(1.0));
		SmartDashboard.putData("Two Bin Auto Test", new PickupBinsFromStep());
		System.out.println("Robot Init Completed");
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit() {
		autoCommand = (Command) autoChooser.getSelected();
		//autoCommand = new PickupBinFromStep();
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
	}
	
	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
