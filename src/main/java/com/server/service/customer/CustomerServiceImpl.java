package com.server.service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.dto.CarDto;
import com.server.entity.Car;
import com.server.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;

    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    };

}
