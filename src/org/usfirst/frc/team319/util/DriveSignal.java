package org.usfirst.frc.team319.util;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * A drivetrain command consisting of the left, right motor settings and whether the brake mode is enabled.
 */
public class DriveSignal {
    protected double leftMotorSignal;
    protected double rightMotorSignal;
    protected boolean brakeMode;

    public DriveSignal(double left, double right) {
        this(left, right, false);
    }

    public DriveSignal(double left, double right, boolean brakeMode) {
        leftMotorSignal = left;
        rightMotorSignal = right;
        this.brakeMode = brakeMode;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, true);

    public double getLeft() {
        return leftMotorSignal;
    }

    public double getRight() {
        return rightMotorSignal;
    }

    public boolean getBrakeMode() {
        return this.brakeMode;
    }

    @Override
    public String toString() {
        return "L: " + leftMotorSignal + ", R: " + rightMotorSignal + (this.brakeMode ? ", BRAKE" : "");
    }
}
