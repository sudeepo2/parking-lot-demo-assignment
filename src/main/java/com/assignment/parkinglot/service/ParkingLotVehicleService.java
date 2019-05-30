package com.assignment.parkinglot.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.parkinglot.bean.BikeParking;
import com.assignment.parkinglot.bean.CarParking;
import com.assignment.parkinglot.bean.ParkingLot;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.enums.VehicleType;
@Service
public interface ParkingLotVehicleService{
	
	public default ParkingLot getParkingLot()
	{
		return new ParkingLot(Arrays.asList(new CarParking("", "C1", LocalDateTime.now(), LocalDateTime.now())), Arrays.asList(new BikeParking("", "B1", LocalDateTime.now(), LocalDateTime.now())));
	}
	public List<VehicleParking> getVehicleParking();
	

}
