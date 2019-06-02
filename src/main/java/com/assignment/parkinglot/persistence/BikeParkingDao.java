package com.assignment.parkinglot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.parkinglot.entity.BikeParkingEntity;

@Repository
public interface BikeParkingDao extends JpaRepository<BikeParkingEntity, Long> {
	BikeParkingEntity findByRegistrationNo(String registrationno);
}
