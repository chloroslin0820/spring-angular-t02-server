package com.server.service.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;
import com.server.entity.Car;
import com.server.entity.CarBooking;
import com.server.entity.User;
import com.server.enums.BookCarStatus;
import com.server.repository.CarBookingRepository;
import com.server.repository.CarRepository;
import com.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarBookingRepository carBookingRepository;

    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    };

    public boolean bookCar(CarBookingDto carBookingDto) {
        Optional<Car> optionalCar = carRepository.findById(carBookingDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(carBookingDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();
            User existingUser = optionalUser.get();
            CarBooking carBooking = new CarBooking();

            carBooking.setCar(existingCar);
            carBooking.setUser(existingUser);
            carBooking.setBookCarStatus(BookCarStatus.PENDING);

            Long diffInMilliSeconds = carBookingDto.getToDate().getTime() - carBookingDto.getFromDate().getTime();
            Long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            carBooking.setDays(days);
            carBooking.setPrice(days * existingCar.getPrice());

            carBookingRepository.save(carBooking);
            return true;
        }
        return false;
    };


}
