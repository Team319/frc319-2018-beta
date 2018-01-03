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
	
	public BobTalonSRX leftLead;
	public BobTalonSRX rightLead;
	public List<BobTalonSRX> leftFollowers;
	public List<BobTalonSRX> rightFollowers;
	
	private boolean _isHighGear;
	
	public DifferentialDrivetrainSubsystem(int leftLeadDeviceNumber, int rightLeadDeviceNumber, 
			int[] leftFollowerDeviceNumbers, int[] rightfollowerDeviceNumbers) {
		this.leftLead = new BobTalonSRX(leftLeadDeviceNumber);
		this.rightLead = new BobTalonSRX(rightLeadDeviceNumber);
		
		for (int i : rightfollowerDeviceNumbers) {
			this.rightFollowers.add(new BobTalonSRX(i));
		}
		
		for (int i : leftFollowerDeviceNumbers) {
			this.leftFollowers.add(new BobTalonSRX(i));
		}
	}
	
	/**
	 * @param leftLead
	 * @param rightLead
	 * @param leftFollowers
	 * @param rightFollowers
	 */
	public DifferentialDrivetrainSubsystem(BobTalonSRX leftLead, BobTalonSRX rightLead, List<BobTalonSRX> leftFollowers, List<BobTalonSRX> rightFollowers) {
		this.leftLead = leftLead;
		this.rightLead = rightLead;
		this.leftFollowers = leftFollowers;
		this.rightFollowers = rightFollowers;
		
		for (BobTalonSRX BobTalonSRX : rightFollowers) {
			BobTalonSRX.follow(rightLead);
		}
		
		for (BobTalonSRX BobTalonSRX : leftFollowers) {
			BobTalonSRX.follow(leftLead);
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
		errorCode = leftLead.setGains(gains, timeoutMs);		
		errorCode = rightLead.setGains(gains, timeoutMs);		
		return errorCode;
	}
	
	public ErrorCode setGains(SRXGains gains) {
		ErrorCode errorCode = ErrorCode.OK;		
		errorCode = leftLead.setGains(gains);		
		errorCode = rightLead.setGains(gains);		
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

		leftLead.setNeutralMode(neutralMode);
		rightLead.setNeutralMode(neutralMode);
		
		for (BobTalonSRX BobTalonSRX : leftFollowers) {
			BobTalonSRX.setNeutralMode(neutralMode);
		}
		
		for (BobTalonSRX BobTalonSRX : rightFollowers) {
			BobTalonSRX.setNeutralMode(neutralMode);
		}
		
		leftLead.set(driveSignal.getControlMode(), driveSignal.getLeft());
		rightLead.set(driveSignal.getControlMode(), driveSignal.getRight());
	}
	
	public boolean getHighGear() {
		return _isHighGear;
	}
	
	public void setHighGear(boolean isHighGear) {
		this._isHighGear = isHighGear;
	}
}
