package com.assignment.parkinglot.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.assignment.parkinglot.bean.BikeParking;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.entity.BikeParkingEntity;
import com.assignment.parkinglot.exception.RecordAlreadyExist;
import com.assignment.parkinglot.exception.RecordNotFoundException;
import com.assignment.parkinglot.persistence.BikeParkingDao;
import com.assignment.parkinglot.service.ParkingLotVehicleService;

@Service("bikeParkingService")
public class BikeParkingService implements ParkingLotVehicleService {
	static List<VehicleParking> bikeParking = Arrays
			.asList(new BikeParking("", "B1", LocalDateTime.now(), LocalDateTime.now()));
	@Autowired
	public BikeParkingDao parkingLotDao;

	public BikeParkingDao getParkingLotDao() {
		return this.parkingLotDao;
	}

	public void setParkingLotDao(BikeParkingDao parkingLotDao) {
		this.parkingLotDao = parkingLotDao;
	}

	@Override
	public List<VehicleParking> getVehicleParking() {
		final List<BikeParkingEntity> parkingLots = this.parkingLotDao.findAll();

		final List<VehicleParking> vehicles = parkingLots.stream()
				.map(bikeParkingEntity -> this.convertToBean(bikeParkingEntity)).collect(Collectors.toList());

		return vehicles;
	}

	@Override
	public VehicleParking admitVehicle(String registrationNo, String entryTime) {
		// TODO Auto-generated method stub
		final BikeParkingEntity bikeParkings = this.parkingLotDao.findByRegistrationNo(registrationNo.toUpperCase());
		if (bikeParkings != null) {
			throw new RecordAlreadyExist(
					"Registration No already Linked With Parking No :" + bikeParkings.getParkingNo());
		}
		final List<BikeParkingEntity> parkingLots = this.parkingLotDao.findAll();
		final OptionalInt indexOpt = IntStream.range(0, parkingLots.size())
				.filter(i -> StringUtils.isEmpty(parkingLots.get(i).getRegistrationNo())).findFirst();
		if (indexOpt.isPresent()) {
			final BikeParkingEntity bikeParkingEntity = parkingLots.get(indexOpt.getAsInt());
			bikeParkingEntity.setRegistrationNo(registrationNo.toUpperCase());
			this.parkingLotDao.save(bikeParkingEntity);
			return this.convertToBean(bikeParkingEntity);
		} else {
			throw new RecordNotFoundException("Parking for car is full");
		}
	}

	@Override
	public VehicleParking getParkingByRegistration(String registrationno) {
		// TODO Auto-generated method stub
		return this.convertToBean(this.parkingLotDao.findByRegistrationNo(registrationno.toUpperCase()));
////		return BikeParkingService.bikeParking.stream()
//				.filter(vehicleParking -> !StringUtils.isEmpty(vehicleParking.getRegistrationNo())
//						&& vehicleParking.getRegistrationNo().equalsIgnoreCase(registrationno))
//				.findFirst();
	}

	@Override
	public void exitVehicle(String registrationNo, String exitTime) {
		// TODO Auto-generated method stub
		final List<BikeParkingEntity> parkingLots = this.parkingLotDao.findAll();
		final OptionalInt indexOpt = IntStream.range(0, parkingLots.size())
				.filter(i -> registrationNo.equalsIgnoreCase(parkingLots.get(i).getRegistrationNo())).findFirst();
		if (indexOpt.isPresent()) {
			final BikeParkingEntity bikeParkingEntity = parkingLots.get(indexOpt.getAsInt());
			bikeParkingEntity.setRegistrationNo(null);
			this.parkingLotDao.save(bikeParkingEntity);
		} else {
			throw new RecordNotFoundException("Parking for bike is not found");
		}
	}

	private BikeParking convertToBean(BikeParkingEntity bikeParkingEntity) {
		// final CarParking vehicleParking = this.modelMapper.map(carParkingEntity,
		// CarParking.class);
		final BikeParking vehicleParking = new BikeParking(bikeParkingEntity.getRegistrationNo(),
				bikeParkingEntity.getParkingNo(), bikeParkingEntity.getEntryTime(), bikeParkingEntity.getExitTime());
//		vehicleParking.setParkingNO(carParkingEntity.getParkingNo());
//		vehicleParking.setRegistrationNo(carParkingEntity.getRegistrationNo());
//		vehicleParking.setEntryTime(carParkingEntity.getEntryTime());
//		vehicleParking.setExitTime(carParkingEntity.getExitTime());
		return vehicleParking;
	}
}
