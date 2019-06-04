package com.assignment.parkinglot.parkinglot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.assignment.parkinglot.bean.ParkingLot;
import com.assignment.parkinglot.bean.VehicleParking;
import com.assignment.parkinglot.bean.VehicleRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ParkingLotApplicationTests {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getParkingLot() throws Exception {
		final ParkingLot response = this.restTemplate
				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot").toString(), ParkingLot.class);

		assertNotNull(String.valueOf(response), response);
	}

	@Test
	void getCarParking() throws Exception {
		final List<VehicleParking> response = this.restTemplate.getForObject(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking").toString(), List.class);
		assertFalse(response.isEmpty());
	}

	@Test
	void getBikeParking() throws Exception {
		final List<VehicleParking> response = this.restTemplate.getForObject(
				new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
		assertFalse(response.isEmpty());
	}

	@Test
	void admitCarWhenParkingFull() throws Exception {
		// CAR PARKING SIZE IN DB IS 10
		VehicleRequest requestEntity = null;
		final Random t = new Random();
		ResponseEntity<VehicleParking> postForEntity = null;
		for (int i = 0; i <= 11; i++) {

			requestEntity = new VehicleRequest("CAR-REGISTRATION-NO-" + t.nextInt(1000), LocalDateTime.now().toString(),
					null);

			postForEntity = this.restTemplate.postForEntity(
					new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(),
					requestEntity, VehicleParking.class);
			System.out.println(postForEntity);
			// assertEquals("Record Not Found", postForEntity.);
			// assertEquals("Parking for car is full", postForEntity.getStatusCode());

		}
		// 11 entry exception is expected
		requestEntity = new VehicleRequest("CAR-REGISTRATION-NO-" + t.nextInt(1000), LocalDateTime.now().toString(),
				null);

		postForEntity = this.restTemplate.postForEntity(
				new URL("http://localhost:" + this.port + "/parkinglot/carparking/admitcar").toString(), requestEntity,
				VehicleParking.class);
		assertEquals(404, postForEntity.getStatusCode());

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
//	@Test
//	void AdmitBikeAndCheckParkingByRegistrationNo() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
//
//	@Test
//	void AdmitBikeAndCheckParkingByDuplicateRegistrationNo() throws Exception {
//		final List<VehicleParking> response = this.restTemplate
//				.getForObject(new URL("http://localhost:" + this.port + "/parkinglot/bikeparking").toString(), List.class);
//		assertFalse(response.isEmpty());
//	}
}
