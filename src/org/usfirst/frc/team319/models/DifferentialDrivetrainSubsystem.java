package org.usfirst.frc.team319.models;

import java.util.List;

import org.usfirst.frc.team319.util.DriveSignal;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author ttremblay
 *
 */
public class DifferentialDrivetrainSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private TalonSRX _leftLead;
	private TalonSRX _rightLead;
	private List<TalonSRX> _leftFollowers;
	private List<TalonSRX> _rightFollowers;
	
	private boolean _isHighGear;
	
	/**
	 * @param leftLead
	 * @param rightLead
	 * @param leftFollowers
	 * @param rightFollowers
	 */
	public DifferentialDrivetrainSubsystem(TalonSRX leftLead, TalonSRX rightLead, List<TalonSRX> leftFollowers, List<TalonSRX> rightFollowers) {
		_leftLead = leftLead;
		_rightLead = rightLead;
		_leftFollowers = leftFollowers;
		_rightFollowers = rightFollowers;
		
		for (TalonSRX talonSRX : _rightFollowers) {
			talonSRX.follow(_rightLead);
		}
		
		for (TalonSRX talonSRX : _leftFollowers) {
			talonSRX.follow(_leftLead);
		}
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	
	public ErrorCode setGains(Gains gains, int timeoutMs) {
		ErrorCode errorCode = ErrorCode.OK;
		errorCode = _leftLead.config_kP(gains.parameterSlot, gains.P, timeoutMs);
		errorCode = _leftLead.config_kI(gains.parameterSlot, gains.I, timeoutMs);
		errorCode = _leftLead.config_kD(gains.parameterSlot, gains.D, timeoutMs);
		errorCode = _leftLead.config_kF(gains.parameterSlot, gains.F, timeoutMs);
		
		errorCode = _rightLead.config_kP(gains.parameterSlot, gains.P, timeoutMs);
		errorCode = _rightLead.config_kI(gains.parameterSlot, gains.I, timeoutMs);
		errorCode = _rightLead.config_kD(gains.parameterSlot, gains.D, timeoutMs);
		errorCode = _rightLead.config_kF(gains.parameterSlot, gains.F, timeoutMs);
		
		return errorCode;
	}
	
	
	/**
	 * @param driveSignal
	 *		The @see com.usfirst.frc.team319.util.DriveSignal to set.
	 */
	public void set(DriveSignal driveSignal) {
		NeutralMode neutralMode = NeutralMode.Coast;
		
		if (driveSignal.getBrakeMode())
		{		
			neutralMode = NeutralMode.Brake;
		}	

		_leftLead.setNeutralMode(neutralMode);
		_rightLead.setNeutralMode(neutralMode);
		
		for (TalonSRX talonSRX : _leftFollowers) {
			talonSRX.setNeutralMode(neutralMode);
		}
		
		for (TalonSRX talonSRX : _rightFollowers) {
			talonSRX.setNeutralMode(neutralMode);
		}
		
		_leftLead.set(driveSignal.getControlMode(), driveSignal.getLeft());
		_rightLead.set(driveSignal.getControlMode(), driveSignal.getRight());
	}
	
	public boolean getHighGear() {
		return _isHighGear;
	}
	
	public void setHighGear(boolean isHighGear) {
		this._isHighGear = isHighGear;
	}
}
