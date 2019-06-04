package com.assignment.parkinglot.bean;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

public class VehicleParking {
	public VehicleParking(String registrationNo, String parkingNO, LocalDateTime localDateTime,
			LocalDateTime localDateTime2) {

		this.registrationNo = registrationNo;
		this.parkingNO = parkingNO;
		this.entryTime = localDateTime;
		this.exitTime = localDateTime2;
	}

	private String registrationNo;
	private String parkingNO;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private boolean occupied;

	public boolean isOccupied() {
		this.occupied = !StringUtils.isEmpty(this.registrationNo);
		return this.occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getParkingNO() {
		return this.parkingNO;
	}

	public void setParkingNO(String parkingNO) {
		this.parkingNO = parkingNO;
	}

	public LocalDateTime getEntryTime() {
		return this.entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return this.exitTime;
	}

	public VehicleParking() {
		super();
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	@Override
	public String toString() {
		return "VehicleParking [registrationNo=" + this.registrationNo + ", parkingNO=" + this.parkingNO
				+ ", entryTime=" + this.entryTime + ", exitTime=" + this.exitTime + ", occupied=" + this.occupied + "]";
	}

}
