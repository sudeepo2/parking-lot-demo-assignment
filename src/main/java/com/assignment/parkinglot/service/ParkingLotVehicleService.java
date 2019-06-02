package com.assignment.parkinglot.service;

import java.util.List;

import com.assignment.parkinglot.bean.VehicleParking;

public interface ParkingLotVehicleService {

	public List<VehicleParking> getVehicleParking();

	public VehicleParking getParkingByRegistration(String registrationNo);

	public VehicleParking admitVehicle(String registrationNo, String entryTime);

	public void exitVehicle(String registrationNo, String exitTime);

}
