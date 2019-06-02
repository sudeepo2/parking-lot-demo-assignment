package com.assignment.parkinglot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.parkinglot.entity.CarParkingEntity;

@Repository
public interface CarParkingDao extends JpaRepository<CarParkingEntity, Long> {

	CarParkingEntity findByRegistrationNo(String registrationno);

}
