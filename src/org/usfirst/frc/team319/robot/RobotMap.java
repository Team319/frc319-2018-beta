package org.usfirst.frc.team319.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
    public static TalonSRX drivetrainLeftLead;
    public static TalonSRX drivetrainRightLead;
    public static TalonSRX drivetrainLeftFollow0;
    public static TalonSRX drivetrainRightFollow0;
    public static TalonSRX drivetrainLeftFollow1;
    public static TalonSRX drivetrainRightFollow1;
    public static TalonSRX drivetrainLeftFollow2;
    public static TalonSRX drivetrainRightFollow2;
    
    public RobotMap() {
		drivetrainLeftLead = new TalonSRX(0);
		drivetrainRightLead = new TalonSRX(1);
		drivetrainLeftFollow0 = new TalonSRX(2);
		drivetrainRightFollow0 = new TalonSRX(3);
		drivetrainLeftFollow1 = new TalonSRX(2);
		drivetrainRightFollow1 = new TalonSRX(3);		
		drivetrainLeftFollow2 = new TalonSRX(2);
		drivetrainRightFollow2 = new TalonSRX(3);		
	}
}
