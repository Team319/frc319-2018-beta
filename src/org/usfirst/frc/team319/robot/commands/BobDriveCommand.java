package org.usfirst.frc.team319.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.util.BobDriveHelper;
import org.usfirst.frc.team319.util.DriveSignal;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class BobDriveCommand extends Command {
	
	private BobDriveHelper helper = new BobDriveHelper();
	
	public BobDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double throttle = Robot.oi.driverController.getLeftStickY();
		double turn = Robot.oi.driverController.getRightStickX();
		boolean quickTurn = Robot.oi.driverController.getRightStickPressed();
		boolean isHighGear = Robot.drivetrain.getHighGear();
		
		DriveSignal driveSignal = helper.bobDrive(throttle, turn, quickTurn, isHighGear, ControlMode.PercentOutput);		
		Robot.drivetrain.set(driveSignal);		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
