package com.assignment.parkinglot.bean;

import java.util.List;

import org.springframework.util.StringUtils;

import com.assignment.parkinglot.enums.VehicleType;

public class ParkingLot {
	
	private List<VehicleParking> carsParking;
	private List<VehicleParking> bikesParking;
	private boolean carParkingAvailabl=true;
	private boolean bikeParkingAvailable=true;
	public boolean isCarParkingAvailable() {
		this.carParkingAvailabl = this.isAvailable(VehicleType.CAR);
		return carParkingAvailabl;
	}
	
	public boolean isBikeParkingAvailable() {
		this.bikeParkingAvailable =this.isAvailable(VehicleType.BIKE);
		return bikeParkingAvailable;
	}
	
	public ParkingLot(List<VehicleParking> carsParking, List<VehicleParking> bikesParking) {
		super();
		this.carsParking = carsParking;
		this.bikesParking = bikesParking;
	}
	public List<VehicleParking> getCarsParking() {
		return carsParking;
	}
	public void setCarsParking(List<VehicleParking> carsParking) {
		this.carsParking = carsParking;
	}
	public List<VehicleParking> getBikesParking() {
		return bikesParking;
	}
	public void setBikesParking(List<VehicleParking> bikesParking) {
		this.bikesParking = bikesParking;
	}
	public boolean isFull(VehicleType type) {
		if(type.equals(VehicleType.CAR)) {
			long parkingOccupiedCount = carsParking.stream().filter(carParking->!StringUtils.isEmpty(carParking.getRegistrationNo())).count();
			return ((parkingOccupiedCount==carsParking.size())?true :false);
		}else {
			long parkingOccupiedCount = bikesParking.stream().filter(bikesParking->!StringUtils.isEmpty(bikesParking.getRegistrationNo())).count();
			return ((parkingOccupiedCount==bikesParking.size())?true :false);
		}
	}
	public boolean isAvailable(VehicleType type) {
		return !isFull(type);
	}
	
}
