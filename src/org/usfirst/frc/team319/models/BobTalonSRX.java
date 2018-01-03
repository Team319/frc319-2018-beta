package org.usfirst.frc.team319.models;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class BobTalonSRX extends TalonSRX {
	
	public static final int DEFAULT_TIMEOUT_MS = 10;
	
	public BobTalonSRX(int deviceNumber) {
		super(deviceNumber);
	}
	
	public ErrorCode setPIDF(int parameterSlot, double p, double i, double d, double f) {
		return this.setPIDF(parameterSlot, p, i, d, f, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode setPIDF(int parameterSlot, double p, double i, double d, double f, int timeoutMs) {
		ErrorCode errorCode = ErrorCode.OK;
		errorCode = this.config_kP(parameterSlot, p, timeoutMs);
		errorCode = this.config_kI(parameterSlot, i, timeoutMs);
		errorCode = this.config_kD(parameterSlot, d, timeoutMs);
		errorCode = this.config_kF(parameterSlot, f, timeoutMs);
		
		return errorCode;
	}
	
	public ErrorCode setGains(SRXGains gains) {
		return this.setPIDF(gains.parameterSlot, gains.P, gains.I, gains.D, gains.F);
	}
	
	public ErrorCode setGains(SRXGains gains, int timeoutMs) {
		return this.setPIDF(gains.parameterSlot, gains.P, gains.I, gains.D, gains.F, timeoutMs);
	}

	public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice, int pidIdx) {
		return super.configSelectedFeedbackSensor(feedbackDevice, pidIdx, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode configNominalOutputForward(double percentOut) {
		return super.configNominalOutputForward(percentOut, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode configNominalOutputReverse(double percentOut) {
		return super.configNominalOutputReverse(percentOut, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode configPeakOutputForward(double percentOut) {
		return super.configPeakOutputForward(percentOut, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode configPeakOutputReverse(double percentOut) {
		return super.configPeakOutputReverse(percentOut, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode configOpenloopRamp(double secondsFromNeutralToFull) {
		return super.configOpenloopRamp(secondsFromNeutralToFull, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode config_kF(int slotIdx, double value) {
		return super.config_kF(slotIdx, value, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode config_kP(int slotIdx, double value) {
		return super.config_kP(slotIdx, value, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode config_kI(int slotIdx, double value) {
		return super.config_kI(slotIdx, value, DEFAULT_TIMEOUT_MS);
	}
	
	public ErrorCode config_kD(int slotIdx, double value) {
		return super.config_kD(slotIdx, value, DEFAULT_TIMEOUT_MS);
	}
	
	public double configGetParameter(ParamEnum param, int ordinal) {
		return super.configGetParameter(param, ordinal, DEFAULT_TIMEOUT_MS);
	}

}
