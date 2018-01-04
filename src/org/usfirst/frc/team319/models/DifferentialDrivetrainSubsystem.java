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


}
