package org.usfirst.frc.team319.robot.subsystems;

import java.util.List;

import org.usfirst.frc.team319.models.BobLeaderTalonSRX;
import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.DifferentialDrivetrainSubsystem;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.commands.BobDriveCommand;
import org.usfirst.frc.team319.util.DriveSignal;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
/**
 * @author ttremblay
 *
 */
public class Drivetrain extends Subsystem{

    public SRXGains HighGearGains = new SRXGains(0, 0.0, 0.0, 0.0, 0.0);
    public SRXGains LowGearGains = new SRXGains(1, 0.0, 0.0, 0.0, 0.0);    
    public static int DEFAULT_TIMEOUT_MS = 10;
    
    private static int leftLeadNum = 0;
    private static int rightLeadNum = 1;
    private static int[] leftFollowerNums = {2,4,6};
    private static int[] rightFollowerNums = {3,5,7};
    

	public BobLeaderTalonSRX leftLead = new BobLeaderTalonSRX(leftLeadNum, leftFollowerNums);
	public BobLeaderTalonSRX rightLead = new BobLeaderTalonSRX(rightLeadNum, rightFollowerNums);
	
	private boolean _isHighGear;
	
	public Drivetrain() { }
	
	
	public Drivetrain(int leftLeadDeviceNumber, int rightLeadDeviceNumber, 
			int[] leftFollowerDeviceNumbers, int[] rightfollowerDeviceNumbers) {
		this.leftLead = new BobLeaderTalonSRX(leftLeadDeviceNumber, leftFollowerDeviceNumbers);
		this.rightLead = new BobLeaderTalonSRX(rightLeadDeviceNumber, rightfollowerDeviceNumbers);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new BobDriveCommand());
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
