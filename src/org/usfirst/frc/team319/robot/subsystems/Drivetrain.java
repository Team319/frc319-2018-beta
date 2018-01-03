package org.usfirst.frc.team319.robot.subsystems;

import java.util.List;
import java.util.Arrays;

import org.usfirst.frc.team319.models.BobTalonSRX;
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
	
    public static BobTalonSRX leftLead = new BobTalonSRX(0);
    public static BobTalonSRX rightLead = new BobTalonSRX(1);
    public static BobTalonSRX leftFollow0 = new BobTalonSRX(2);
    public static BobTalonSRX rightFollow0 = new BobTalonSRX(3);
    public static BobTalonSRX leftFollow1 = new BobTalonSRX(4);
    public static BobTalonSRX rightFollow1 = new BobTalonSRX(5);
    public static BobTalonSRX leftFollow2 = new BobTalonSRX(6);
    public static BobTalonSRX rightFollow2 = new BobTalonSRX(7);

    public static List<BobTalonSRX> leftFollowers = Arrays.asList(leftFollow0, leftFollow1, leftFollow2);
    public static List<BobTalonSRX> rightFollowers = Arrays.asList(rightFollow0, rightFollow1, rightFollow2);

    public SRXGains HighGearGains = new SRXGains(0, 0.0, 0.0, 0.0, 0.0);
    public SRXGains LowGearGains = new SRXGains(1, 0.0, 0.0, 0.0, 0.0);
    
    public static int DEFAULT_TIMEOUT_MS = 10;
	
	/**
	 * @param leftLead The left lead BobTalonSRX.
	 * @param rightLead The right lead BobTalonSRX.
	 * @param leftFollowers A list of BobTalonSRXs that will follow leftLead
	 * @param rightFollowers A list of BobTalonSRXs that will follow rightLead
	 */
	public Drivetrain() {
		super(leftLead, rightLead, leftFollowers, rightFollowers);
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
