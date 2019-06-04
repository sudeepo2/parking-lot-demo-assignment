package com.assignment.parkinglot.parkinglot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.parkinglot.bean.ParkingLot;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.bean.VehicleRequest;
import com.assignment.parkinglot.exception.ExceptionResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class ParkingLotApplicationTests {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	private static List<String> admitedCars = new ArrayList<String>();

	@Test
	@Order(1)
	void getParkingLot() throws Exception {
		final ParkingLot response = this.restTemplate
				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot").toString(), ParkingLot.class);

		assertNotNull(String.valueOf(response), response);
	}

	@Test
	@Order(2)
	void getCarParking() throws Exception {
		final List<VehicleParking> response = this.restTemplate.getForObject(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking").toString(), List.class);
		assertFalse(response.isEmpty());
	}

	@Test
	@Order(3)
	void getBikeParking() throws Exception {
		final List<VehicleParking> response = this.restTemplate.getForObject(
				new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
		assertFalse(response.isEmpty());
	}

	@Test
	@Order(4)
	void admitCarWhenParkingFull() throws Exception {
		// CAR PARKING SIZE IN DB IS 10
		VehicleRequest requestEntity = null;
		final Random t = new Random();
		ResponseEntity<ExceptionResponse> postForEntityError = null;
		for (int i = 0; i <= 10; i++) {
			final String registrationNo = "CAR-REGISTRATION-NO-" + t.nextInt(1000);
			ParkingLotApplicationTests.admitedCars.add(registrationNo);
			requestEntity = new VehicleRequest(registrationNo, LocalDateTime.now().toString(), null);

			this.restTemplate.postForEntity(
					new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(),
					requestEntity, VehicleParking.class);

		}
		// 11 entry exception is expected
		requestEntity = new VehicleRequest("CAR-REGISTRATION-NO-" + t.nextInt(1000), LocalDateTime.now().toString(),
				null);

		postForEntityError = this.restTemplate.postForEntity(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(), requestEntity,
				ExceptionResponse.class);
		assertEquals(HttpStatus.NOT_FOUND, postForEntityError.getStatusCode());
		assertEquals("Record Not Found", postForEntityError.getBody().getMessage());
		assertEquals("[Parking for car is full]", postForEntityError.getBody().getDetails().toString());

	}

	@Test
	@Order(5)
	void exitCar() throws Exception {
		// Empty Parking
		final List<String> temp = new ArrayList<String>();
		for (final String registrationNo : ParkingLotApplicationTests.admitedCars) {
			final Map<String, String> param = new HashMap();
			param.put("registrationNo", registrationNo);
			this.restTemplate
					.delete(new URL("http://localhost:" + this.port + "/parkinglot/carparking/exitcar/{registrationNo}")
							.toString(), param);
			temp.add(registrationNo);
		}
		ParkingLotApplicationTests.admitedCars.removeAll(temp);
		assertEquals(0, ParkingLotApplicationTests.admitedCars.size());

	}

	@Test
	@Order(6)
	void AdmitCarAndCheckDuplicateParkingByRegistrationNo() throws Exception {
		VehicleRequest requestEntity = null;
		final Random t = new Random();
		ResponseEntity<VehicleParking> postForEntity = null;
		ResponseEntity<ExceptionResponse> postForEntityError = null;

		final String registrationNo = "CAR-REGISTRATION-NO-" + t.nextInt(1000);
		requestEntity = new VehicleRequest(registrationNo, LocalDateTime.now().toString(), null);

		postForEntity = this.restTemplate.postForEntity(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(), requestEntity,
				VehicleParking.class);
		System.out.println(postForEntity);

		// 11 entry exception is expected
		// requestEntity = new VehicleRequest(registrationNo,
		// LocalDateTime.now().toString(), null);

		postForEntityError = this.restTemplate.postForEntity(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(), requestEntity,
				ExceptionResponse.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, postForEntityError.getStatusCode());
		assertEquals("Server Error", postForEntityError.getBody().getMessage());
		assertEquals("Registration No already Linked With Parking No :C1",
				postForEntityError.getBody().getDetails().toString());
	}

//	@Test
//	void admitBikeWhenParkingFull() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//	@Test
//	void checkParkingAvailibilityForCar() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//	@Test
//	void checkParkingAvailibilityForBike() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//	@Test
//	void AdmitCarAndCheckParkingByRegistrationNo() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//	@Test
//	void AdmitCarAndCheckParkingByDuplicateRegistrationNo() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//
//	@Test
//	void AdmitBikeAndCheckParkingByDuplicateRegistrationNo() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
}
