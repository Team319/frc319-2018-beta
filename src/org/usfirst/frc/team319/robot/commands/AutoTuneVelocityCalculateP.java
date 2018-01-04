package org.usfirst.frc.team319.robot.commands;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.util.BobCircularBuffer;
import org.usfirst.frc.team319.util.HelperFunctions;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTuneVelocityCalculateP extends Command {
	
	private int samplesRequired;
	private int samplesGathered = 0;
	private int paramterSlot = 0;
	
	private BobTalonSRX _talon;
	private StringBuilder _sb;
	private BobCircularBuffer cBuff;


    public AutoTuneVelocityCalculateP(BobTalonSRX talon, int srxParameterSlot, double desiredVelocity, int numSamplesRequired) {
    	this._talon = talon;
    	this.samplesRequired = numSamplesRequired;
    	this.cBuff = new BobCircularBuffer(samplesRequired);
    	this.paramterSlot = srxParameterSlot;
    	this.target = desiredVelocity;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Gathering Data...");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double outputSignal = _talon.getMotorOutputVoltage() / _talon.getBusVoltage();
    	double speed = _talon.getSelectedSensorVelocity(paramterSlot);
    	double closedLoopError = _talon.getClosedLoopError(paramterSlot);
    	
    	cBuff.pushBack(closedLoopError);
    	samplesGathered++;
    	
    	_sb.append("\tOutput: ");
    	_sb.append(outputSignal);
    	_sb.append("\tSpeed: ");
    	_sb.append(speed);
    	_sb.append("\tError: ");
    	_sb.append(speed);
    	_sb.append("\tTarget: ");
    	_sb.append(speed);
    	
    	System.out.println(_sb.toString());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return samplesGathered >= samplesRequired;
    }

    // Called once after isFinished returns true
    protected void end() {
    	double kP = 0.1 * 1023 / HelperFunctions.max(cBuff.toArray());
    	_talon.config_kP(paramterSlot, kP);
    	System.out.println("Calculated P gain = " + kP);    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
