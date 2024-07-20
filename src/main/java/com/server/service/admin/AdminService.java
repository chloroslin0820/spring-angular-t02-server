package com.server.service.admin;

import java.util.List;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;

public interface AdminService {

    boolean postCar(CarDto carDto);

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long id, CarDto carDto);

    List<CarBookingDto> getBookings();
}
