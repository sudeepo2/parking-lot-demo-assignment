package com.assignment.parkinglot.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "car_parking")
public class CarParkingEntity {
	@Id
	@GeneratedValue
	private long id;
	private String registrationNo;
	private String parkingNo;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private boolean occupied;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getParkingNo() {
		return this.parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
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

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public boolean isOccupied() {
		return this.occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
