package org.usfirst.frc.team319.robot.subsystems;

import java.util.List;
import java.util.Arrays;

import org.usfirst.frc.team319.models.DifferentialDrivetrainSubsystem;
import org.usfirst.frc.team319.models.Gains;
import org.usfirst.frc.team319.robot.commands.BobDriveCommand;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
/**
 * @author ttremblay
 *
 */
public class Drivetrain extends DifferentialDrivetrainSubsystem {
	
    public static TalonSRX leftLead = new TalonSRX(0);
    public static TalonSRX rightLead = new TalonSRX(1);
    public static TalonSRX leftFollow0 = new TalonSRX(2);
    public static TalonSRX rightFollow0 = new TalonSRX(3);
    public static TalonSRX leftFollow1 = new TalonSRX(4);
    public static TalonSRX rightFollow1 = new TalonSRX(5);
    public static TalonSRX leftFollow2 = new TalonSRX(6);
    public static TalonSRX rightFollow2 = new TalonSRX(7);

    public static List<TalonSRX> leftFollowers = Arrays.asList(leftFollow0, leftFollow1, leftFollow2);
    public static List<TalonSRX> rightFollowers = Arrays.asList(rightFollow0, rightFollow1, rightFollow2);

    public Gains HighGearGains = new Gains(0, 0.0, 0.0, 0.0, 0.0);
    public Gains LowGearGains = new Gains(1, 0.0, 0.0, 0.0, 0.0);
    
    public static int DEFAULT_TIMEOUT_MS = 10;
	
	/**
	 * @param leftLead The left lead TalonSRX.
	 * @param rightLead The right lead TalonSRX.
	 * @param leftFollowers A list of TalonSRXs that will follow leftLead
	 * @param rightFollowers A list of TalonSRXs that will follow rightLead
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
