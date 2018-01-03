package org.usfirst.frc.team319.util;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * A drivetrain command consisting of the left, right motor settings and whether the brake mode is enabled.
 */
public class DriveSignal {
	protected ControlMode controlMode;
    protected double leftMotorSignal;
    protected double rightMotorSignal;
    protected boolean brakeMode;

    public DriveSignal(double left, double right) {
        this(left, right, false, ControlMode.PercentOutput);
    }

    public DriveSignal(double left, double right, boolean brakeMode) {
    	this(left, right, brakeMode, ControlMode.PercentOutput);        
    }
    
    public DriveSignal(double left, double right, ControlMode controlMode) {
    	this(left, right, false, controlMode);
	}
    
    public DriveSignal(double left, double right, boolean brakeMode, ControlMode controlMode) {
    	this.leftMotorSignal = left;
    	this.rightMotorSignal = right;
    	this.brakeMode = brakeMode;
    	this.controlMode = controlMode;
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
    
    public ControlMode getControlMode() {
    	return this.controlMode;
    }
    
    public void setControlMode(ControlMode controlMode) {
    	this.controlMode = controlMode;
    }
}
