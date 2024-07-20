package com.server.service.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;
import com.server.entity.Car;
import com.server.entity.CarBooking;
import com.server.repository.CarBookingRepository;
import com.server.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;
    private final CarBookingRepository carBookingRepository;

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

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    };

    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDto).orElse(null);
    };

    @Override
    public boolean updateCar(Long id, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
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
                try {
                    car.setImage(imageFile.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            carRepository.save(car);
            return true;
        } else {
            return false;
        }
    };

    @Override
    public List<CarBookingDto> getBookings() {
        return carBookingRepository
                .findAll()
                .stream()
                .map(CarBooking::getCarBookingDto)
                .collect(Collectors.toList());
    };



}
