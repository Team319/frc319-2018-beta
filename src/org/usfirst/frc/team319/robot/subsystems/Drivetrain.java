package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.models.DifferentialDrivetrainSubsystem;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.commands.BobDriveCommand;

/**
 *
 */
/**
 * @author ttremblay
 *
 */
public class Drivetrain extends DifferentialDrivetrainSubsystem {

    public SRXGains HighGearGains = new SRXGains(0, 0.0, 0.0, 0.0, 0.0);
    public SRXGains LowGearGains = new SRXGains(1, 0.0, 0.0, 0.0, 0.0);    
    public static int DEFAULT_TIMEOUT_MS = 10;
    
    private static int leftLeadNum = 0;
    private static int rightLeadNum = 1;
    private static int[] leftFollowerNums = {2,4,6};
    private static int[] rightFollowerNums = {3,5,7};
	
	/**
	 * @param leftLead The left lead BobTalonSRX.
	 * @param rightLead The right lead BobTalonSRX.
	 * @param leftFollowers A list of BobTalonSRXs that will follow leftLead
	 * @param rightFollowers A list of BobTalonSRXs that will follow rightLead
	 */
	public Drivetrain() {	    
		super(leftLeadNum, rightLeadNum, leftFollowerNums, rightFollowerNums);
		super.setGains(HighGearGains, DEFAULT_TIMEOUT_MS);
		super.setGains(LowGearGains, DEFAULT_TIMEOUT_MS);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team319.models.DifferentialDrivetrain#initDefaultCommand()
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new BobDriveCommand());
	}
}
