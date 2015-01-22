package com._2491nomythic.helios.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com._2491nomythic.helios.OI;
import com._2491nomythic.helios.subsystems.Arm;
import com._2491nomythic.helios.subsystems.Drivetrain;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Arm arm;

	public static void init() {
		drivetrain = Drivetrain.getInstance();
		arm = Arm.getInstance();
		// This MUST be here. If the OI creates Commands (which it very likely
		// will), constructing it during the construction of CommandBase (from
		// which commands extend), subsystems are not guaranteed to be
		// yet. Thus, their requires() statements may grab null pointers. Bad
		// news. Don't move it.
		oi = new OI();
		oi.init();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(arm);
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}
