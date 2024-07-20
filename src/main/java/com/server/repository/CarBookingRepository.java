package com.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.entity.CarBooking;

@Repository
public interface CarBookingRepository extends JpaRepository<CarBooking, Long>{

    List<CarBooking> findAllByUserId(Long userId);
}
