package org.usfirst.frc.team319.robot.commands;

import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftDownCommand extends Command {
	
	private boolean isHighGear;

    public ShiftDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isHighGear = Robot.drivetrain.getHighGear();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (isHighGear) {
    		Robot.drivetrain.setHighGear(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
