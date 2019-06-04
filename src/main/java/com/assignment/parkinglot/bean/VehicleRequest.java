package com.assignment.parkinglot.bean;

public class VehicleRequest {
	private String registrationNo;
	private String entryTime;
	private String exitTime;

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public String getEntryTime() {
		return this.entryTime;
	}

	public String getExitTime() {
		return this.exitTime;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public VehicleRequest(String registrationNo, String entryTime, String exitTime) {
		super();
		this.registrationNo = registrationNo;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}

}
