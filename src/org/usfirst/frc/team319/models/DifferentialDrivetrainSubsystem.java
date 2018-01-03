package org.usfirst.frc.team319.models;

import java.util.List;

import org.usfirst.frc.team319.util.DriveSignal;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author ttremblay
 *
 */
public class DifferentialDrivetrainSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private BobTalonSRX _leftLead;
	private BobTalonSRX _rightLead;
	private List<BobTalonSRX> _leftFollowers;
	private List<BobTalonSRX> _rightFollowers;
	
	private boolean _isHighGear;
	
	/**
	 * @param leftLead
	 * @param rightLead
	 * @param leftFollowers
	 * @param rightFollowers
	 */
	public DifferentialDrivetrainSubsystem(BobTalonSRX leftLead, BobTalonSRX rightLead, List<BobTalonSRX> leftFollowers, List<BobTalonSRX> rightFollowers) {
		_leftLead = leftLead;
		_rightLead = rightLead;
		_leftFollowers = leftFollowers;
		_rightFollowers = rightFollowers;
		
		for (BobTalonSRX BobTalonSRX : _rightFollowers) {
			BobTalonSRX.follow(_rightLead);
		}
		
		for (BobTalonSRX BobTalonSRX : _leftFollowers) {
			BobTalonSRX.follow(_leftLead);
		}
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	
	public ErrorCode setGains(SRXGains gains, int timeoutMs) {
		ErrorCode errorCode = ErrorCode.OK;		
		errorCode = _leftLead.setGains(gains, timeoutMs);		
		errorCode = _rightLead.setGains(gains, timeoutMs);		
		return errorCode;
	}
	
	public ErrorCode setGains(SRXGains gains) {
		ErrorCode errorCode = ErrorCode.OK;		
		errorCode = _leftLead.setGains(gains);		
		errorCode = _rightLead.setGains(gains);		
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
		
		for (BobTalonSRX BobTalonSRX : _leftFollowers) {
			BobTalonSRX.setNeutralMode(neutralMode);
		}
		
		for (BobTalonSRX BobTalonSRX : _rightFollowers) {
			BobTalonSRX.setNeutralMode(neutralMode);
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
