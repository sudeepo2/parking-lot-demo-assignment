package com.assignment.parkinglot.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.assignment.parkinglot.bean.CarParking;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.entity.CarParkingEntity;
import com.assignment.parkinglot.exception.RecordAlreadyExist;
import com.assignment.parkinglot.exception.RecordNotFoundException;
import com.assignment.parkinglot.persistence.CarParkingDao;
import com.assignment.parkinglot.service.ParkingLotVehicleService;

@Service("carParkingService")
public class CarParkingService implements ParkingLotVehicleService {
	Logger logger = LoggerFactory.getLogger(CarParkingService.class);
	static List<VehicleParking> carsParking = Arrays
			.asList(new CarParking("", "C1", LocalDateTime.now(), LocalDateTime.now()));
	@Autowired
	public CarParkingDao parkingLotDao;

	public CarParkingDao getParkingLotDao() {
		return this.parkingLotDao;
	}

	public void setParkingLotDao(CarParkingDao parkingLotDao) {
		this.parkingLotDao = parkingLotDao;
	}

	@Override
	public List<VehicleParking> getVehicleParking() {
		// TODO Auto-generated method stub
		final List<CarParkingEntity> parkingLots = this.parkingLotDao.findAll();

		final List<VehicleParking> vehicles = parkingLots.stream()
				.map(carParkingEntity -> this.convertToBean(carParkingEntity)).collect(Collectors.toList());

		return vehicles;
		// return CarParkingService.carsParking;
	}

	@Override
	public VehicleParking admitVehicle(String registrationNo, String entryTime) {
		// TODO Auto-generated method stub
		final CarParkingEntity parkingLots = this.parkingLotDao.findByRegistrationNo(registrationNo.toUpperCase());
		if (parkingLots != null) {
			throw new RecordAlreadyExist(
					"Registration No already Linked With Parking No :" + parkingLots.getParkingNo());
		}
		final List<CarParkingEntity> carParkings = this.parkingLotDao.findAll();
		final OptionalInt indexOpt = IntStream.range(0, carParkings.size())
				.filter(i -> StringUtils.isEmpty(carParkings.get(i).getRegistrationNo())).findFirst();
		if (indexOpt.isPresent()) {
			final CarParkingEntity carParkingEntity = carParkings.get(indexOpt.getAsInt());
			carParkingEntity.setRegistrationNo(registrationNo.toUpperCase());
			this.parkingLotDao.save(carParkingEntity);
			return this.convertToBean(carParkingEntity);
		} else {
			throw new RecordNotFoundException("Parking for car is full");
		}

	}

	@Override
	public VehicleParking getParkingByRegistration(String registrationno) {
		// TODO Auto-generated method stub
		return this.convertToBean(this.parkingLotDao.findByRegistrationNo(registrationno.toUpperCase()));
//		return CarParkingService.carsParking.stream()
//				.filter(vehicleParking -> !StringUtils.isEmpty(vehicleParking.getRegistrationNo())
//						&& vehicleParking.getRegistrationNo().equalsIgnoreCase(registrationno))
//				.findFirst();
	}

	@Override
	public void exitVehicle(String registrationNo, String exitTime) {
		// TODO Auto-generated method stub
		final List<CarParkingEntity> parkingLots = this.parkingLotDao.findAll();
		final OptionalInt indexOpt = IntStream.range(0, parkingLots.size())
				.filter(i -> registrationNo.equalsIgnoreCase(parkingLots.get(i).getRegistrationNo())).findFirst();
		if (indexOpt.isPresent()) {
			final CarParkingEntity carParkingEntity = parkingLots.get(indexOpt.getAsInt());
			carParkingEntity.setRegistrationNo(null);
			this.parkingLotDao.save(carParkingEntity);
		} else {
			throw new RecordNotFoundException("Parking for car not found");
		}
	}

	private CarParking convertToBean(CarParkingEntity carParkingEntity) {
		// final CarParking vehicleParking = this.modelMapper.map(carParkingEntity,
		// CarParking.class);
		final CarParking vehicleParking = new CarParking(carParkingEntity.getRegistrationNo(),
				carParkingEntity.getParkingNo(), carParkingEntity.getEntryTime(), carParkingEntity.getExitTime());
//		vehicleParking.setParkingNO(carParkingEntity.getParkingNo());
//		vehicleParking.setRegistrationNo(carParkingEntity.getRegistrationNo());
//		vehicleParking.setEntryTime(carParkingEntity.getEntryTime());
//		vehicleParking.setExitTime(carParkingEntity.getExitTime());
		return vehicleParking;
	}

//	private CarParkingEntity convertToEntity(CarParking carParking) {
//		final ParkingLotEntity carParkingEntity = this.modelMapper.map(carParking, CarParkingEntity.class);
//		carParkingEntity.setSubmissionDate(
//				carParking.getSubmissionDateConverted(carParking.getCurrentUser().getPreference().getTimezone()));
//
//		if (carParkingEntity.getId() != null) {
//			final Post oldPost = postService.getPostById(postDto.getId());
//			post.setRedditID(oldPost.getRedditID());
//			post.setSent(oldPost.isSent());
//		}
//		return post;
//	}

}
