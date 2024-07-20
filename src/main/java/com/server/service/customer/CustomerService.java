package com.server.service.customer;

import java.util.List;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;

public interface CustomerService {

    List<CarDto> getAllCars();

    public boolean bookCar(Long carId, CarBookingDto carBookingDto);

    CarDto getCarById(Long id);

    List<CarBookingDto> getBookingsByUserId(Long userId);
}
