package com.assignment.parkinglot.bean;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.util.StringUtils;

public abstract class VehicleParking {
	public VehicleParking(String registrationNo, String parkingNO, LocalDateTime localDateTime, LocalDateTime localDateTime2) {
		super();
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
		this.occupied=!StringUtils.isEmpty(this.registrationNo);
		return this.occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getParkingNO() {
		return parkingNO;
	}
	public void setParkingNO(String parkingNO) {
		this.parkingNO = parkingNO;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalDateTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

}
