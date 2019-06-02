package com.assignment.parkinglot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.parkinglot.bean.ParkingLot;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.bean.VehicleRequest;
import com.assignment.parkinglot.service.ParkingLotVehicleService;

@RestController
public class ParkingLotController {

	@Autowired
	private ParkingLotVehicleService carParkingService;
	@Autowired
	private ParkingLotVehicleService bikeParkingService;

	@GetMapping("/parkinglot")
	public ParkingLot getParkingLot() {
		return new ParkingLot(this.carParkingService.getVehicleParking(), this.bikeParkingService.getVehicleParking());
	}

	@GetMapping("/parkinglot/carparking")
	public List<VehicleParking> getCarParking() {
		return this.carParkingService.getVehicleParking();

	}

	@GetMapping("/parkinglot/bikeparking")
	public List<VehicleParking> getBikeParking() {
		return this.bikeParkingService.getVehicleParking();

	}

	@GetMapping("/parkinglot/carparking/{registrationno}")
	public ResponseEntity<VehicleParking> getCarParkingUsingRegistration(@PathVariable String registrationno) {
		return new ResponseEntity<VehicleParking>(this.carParkingService.getParkingByRegistration(registrationno),
				HttpStatus.OK);

	}

	@GetMapping("/parkinglot/bikeparking/{registrationno}")
	public ResponseEntity<VehicleParking> getBikeParkingUsingRegistration(@PathVariable String registrationno) {
		return new ResponseEntity<VehicleParking>(this.bikeParkingService.getParkingByRegistration(registrationno),
				HttpStatus.OK);

	}

	@PostMapping("/parkinglot/carparking/admitcar")
	public ResponseEntity<VehicleParking> admitCar(@RequestBody VehicleRequest request) {
		return new ResponseEntity<VehicleParking>(
				this.carParkingService.admitVehicle(request.getRegistrationNo(), request.getEntryTime()),
				HttpStatus.OK);

	}

	@PostMapping("/parkinglot/bikeparking/admitbike")
	public ResponseEntity<VehicleParking> admitBike(@RequestBody VehicleRequest request) {
		return new ResponseEntity<VehicleParking>(
				this.bikeParkingService.admitVehicle(request.getRegistrationNo(), request.getEntryTime()),
				HttpStatus.OK);

	}

	@DeleteMapping("/parkinglot/carparking/exitcar")
	public void exitCar(@RequestBody VehicleRequest request) {
		this.carParkingService.exitVehicle(request.getRegistrationNo(), request.getExitTime());

	}

	@DeleteMapping("/parkinglot/bikeparking/exitbike")
	public void exitBike(@RequestBody VehicleRequest request) {

		this.bikeParkingService.exitVehicle(request.getRegistrationNo(), request.getExitTime());

	}
}
