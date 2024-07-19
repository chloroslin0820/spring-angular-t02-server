package com.server.service.admin;

import java.util.List;

import com.server.dto.CarDto;

public interface AdminService {

    boolean postCar(CarDto carDto);

    List<CarDto> getAllCars();

    void deleteCar(Long id);
}
