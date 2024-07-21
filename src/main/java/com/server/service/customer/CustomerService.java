package com.server.service.customer;

import java.util.List;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;
import com.server.dto.CarDtoListDto;
import com.server.dto.SearchCarDto;

public interface CustomerService {

    List<CarDto> getAllCars();

    public boolean bookCar(Long carId, CarBookingDto carBookingDto);

    CarDto getCarById(Long id);

    List<CarBookingDto> getBookingsByUserId(Long userId);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);

}
