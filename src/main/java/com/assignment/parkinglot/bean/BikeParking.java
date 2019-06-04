package com.assignment.parkinglot.bean;

import java.time.LocalDateTime;

public class BikeParking extends VehicleParking {

	public BikeParking(String registrationNo, String parkingNo, LocalDateTime entryTime, LocalDateTime exitTime) {
		super(registrationNo, parkingNo, entryTime, exitTime);
		// TODO Auto-generated constructor stub
	}

	public BikeParking() {
		super();
		// TODO Auto-generated constructor stub
	}

}
