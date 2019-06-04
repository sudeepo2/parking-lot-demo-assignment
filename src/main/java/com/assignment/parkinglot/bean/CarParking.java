package com.assignment.parkinglot.bean;

import java.time.LocalDateTime;

public class CarParking extends VehicleParking {

	public CarParking(String registrationNo, String parkingNo, LocalDateTime entryTime, LocalDateTime exitTime) {
		super(registrationNo, parkingNo, entryTime, exitTime);
		// TODO Auto-generated constructor stub
	}

	public CarParking() {
		super();
		// TODO Auto-generated constructor stub
	}

}
