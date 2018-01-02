package org.usfirst.frc.team319.robot.subsystems;

import java.util.List;

import org.usfirst.frc.team319.robot.commands.BobDriveCommand;
import org.usfirst.frc.team319.util.DriveSignal;

import com.ctre.phoenix.drive.DiffDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDController.NullTolerance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private TalonSRX _leftLead;
	private TalonSRX _rightLead;
	private List<TalonSRX> _leftFollowers;
	private List<TalonSRX> _rightFollowers;
	
	
	private DiffDrive _diffDrive = new DiffDrive(_leftLead, _rightLead);
	
	private boolean _isHighGear;
	
	public Drivetrain(TalonSRX leftLead, TalonSRX rightLead, List<TalonSRX> leftFollowers, List<TalonSRX> rightFollowers) {
		_leftLead = leftLead;
		_rightLead = rightLead;
		_leftFollowers = leftFollowers;
		_rightFollowers = rightFollowers;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new BobDriveCommand());
	}
	
	public void set(ControlMode controlMode, DriveSignal driveSignal) {
		NeutralMode neutralMode = NeutralMode.Coast;
		
		if (driveSignal.getBrakeMode())
		{		
			neutralMode = NeutralMode.Brake;
		}	

		_leftLead.setNeutralMode(NeutralMode.Brake);
		_rightLead.setNeutralMode(NeutralMode.Brake);
		
		for (TalonSRX talonSRX : _leftFollowers) {
			talonSRX.setNeutralMode(neutralMode);
		}
		
		for (TalonSRX talonSRX : _rightFollowers) {
			talonSRX.setNeutralMode(neutralMode);
		}
		
		_leftLead.set(controlMode, driveSignal.getLeft());
		_rightLead.set(controlMode, driveSignal.getRight());
	}
	
	public boolean getHighGear() {
		return _isHighGear;
	}
	
	public void setHighGear(boolean isHighGear) {
		this._isHighGear = isHighGear;
	}
}
