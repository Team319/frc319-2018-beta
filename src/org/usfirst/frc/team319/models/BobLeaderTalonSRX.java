package org.usfirst.frc.team319.models;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public class BobLeaderTalonSRX extends BobTalonSRX{
	
	private List<BobTalonSRX> followers;
	
	public BobLeaderTalonSRX(int leadDeviceNumber, int[] followerDeviceNumbers) {
		super(leadDeviceNumber);
		
		followers = new ArrayList<BobTalonSRX>();
		
		for (int i : followerDeviceNumbers) {
			BobTalonSRX follower = new BobTalonSRX(followerDeviceNumbers[i]);
			follower.follow(this);
			followers.add(follower);
		}
	}
	
	public List<BobTalonSRX> getFollowers(){
		return this.followers;
	}
	
	public void setNeutralMode(NeutralMode neutralMode) {
		this.setNeutralMode(neutralMode);
		for (BobTalonSRX follower : followers) {
			follower.setNeutralMode(neutralMode);
		}
	}

}
