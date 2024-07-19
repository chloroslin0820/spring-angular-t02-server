package com.server.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.dto.CarDto;
import com.server.entity.Car;
import com.server.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto) {

        try {

            Car car = new Car();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setYear(carDto.getYear());
            car.setPrice(carDto.getPrice());
            car.setType(carDto.getType());
            car.setTransmission(carDto.getTransmission());
            car.setDescription(carDto.getDescription());

            MultipartFile imageFile = carDto.getImage();
            if (imageFile != null && !imageFile.isEmpty()) {
                car.setImage(imageFile.getBytes());
            }

            carRepository.save(car);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository
                .findAll()
                .stream()
                .map(Car::getCarDto)
                .collect(Collectors.toList());
    };

}
