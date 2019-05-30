package com.assignment.parkinglot.restcontroller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.parkinglot.bean.BikeParking;
import com.assignment.parkinglot.bean.CarParking;
import com.assignment.parkinglot.bean.ParkingLot;
import com.assignment.parkinglot.bean.VehicleParking;

@RestController
public class ParkingLotController {
	
	@GetMapping("/parkinglot")
public ParkingLot getParkingLot() {
		return new ParkingLot(Arrays.asList(new CarParking("", "C1", LocalDateTime.now(), LocalDateTime.now())), Arrays.asList(new BikeParking("", "B1", LocalDateTime.now(), LocalDateTime.now())));
	}
	@GetMapping("/parkinglot/carparking")
public List<VehicleParking> getCarParking(){
		return Arrays.asList(new CarParking("", "C1", LocalDateTime.now(), LocalDateTime.now()));
		
	}
	
	@GetMapping("/parkinglot/bikeparking")
	public List<VehicleParking> getBikeParking(){
			return Arrays.asList(new BikeParking("", "B1", LocalDateTime.now(), LocalDateTime.now()));
			
		}
	
}
