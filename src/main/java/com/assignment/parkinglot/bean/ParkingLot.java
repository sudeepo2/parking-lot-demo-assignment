package com.assignment.parkinglot.bean;

import java.util.List;

import org.springframework.util.StringUtils;

import com.assignment.parkinglot.enums.VehicleType;

public class ParkingLot {

	private List<VehicleParking> carsParking;
	private List<VehicleParking> bikesParking;
	private boolean carParkingAvailabl = true;
	private boolean bikeParkingAvailable = true;

	public boolean isCarParkingAvailable() {
		this.carParkingAvailabl = this.isAvailable(VehicleType.CAR);
		return this.carParkingAvailabl;
	}

	public boolean isBikeParkingAvailable() {
		this.bikeParkingAvailable = this.isAvailable(VehicleType.BIKE);
		return this.bikeParkingAvailable;
	}

	public ParkingLot(List<VehicleParking> carsParking, List<VehicleParking> bikesParking) {
		super();
		this.carsParking = carsParking;
		this.bikesParking = bikesParking;
	}

	public List<VehicleParking> getCarsParking() {
		return this.carsParking;
	}

	public void setCarsParking(List<VehicleParking> carsParking) {
		this.carsParking = carsParking;
	}

	public List<VehicleParking> getBikesParking() {
		return this.bikesParking;
	}

	public void setBikesParking(List<VehicleParking> bikesParking) {
		this.bikesParking = bikesParking;
	}

	public boolean isFull(VehicleType type) {
		if (type.equals(VehicleType.CAR)) {
			final long parkingOccupiedCount = this.carsParking.stream()
					.filter(carParking -> !StringUtils.isEmpty(carParking.getRegistrationNo())).count();
			return ((parkingOccupiedCount == this.carsParking.size()) ? true : false);
		} else {
			final long parkingOccupiedCount = this.bikesParking.stream()
					.filter(bikesParking -> !StringUtils.isEmpty(bikesParking.getRegistrationNo())).count();
			return ((parkingOccupiedCount == this.bikesParking.size()) ? true : false);
		}
	}

	public boolean isAvailable(VehicleType type) {
		return !this.isFull(type);
	}

	@Override
	public String toString() {
		return "ParkingLot [carsParking=" + this.carsParking + ", bikesParking=" + this.bikesParking
				+ ", carParkingAvailabl=" + this.carParkingAvailabl + ", bikeParkingAvailable="
				+ this.bikeParkingAvailable + "]";
	}

}
