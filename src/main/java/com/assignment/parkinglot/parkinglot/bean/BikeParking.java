package com.assignment.parkinglot.parkinglot.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class BikeParking extends VehicleParking{

	public BikeParking(String registrationNo, String parkingNO, LocalDateTime entryTime, LocalDateTime exitTime) {
		super(registrationNo, parkingNO, entryTime, exitTime);
		// TODO Auto-generated constructor stub
	}

}
