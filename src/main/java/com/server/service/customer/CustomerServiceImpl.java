package com.server.service.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.server.dto.CarBookingDto;
import com.server.dto.CarDto;
import com.server.dto.CarDtoListDto;
import com.server.dto.SearchCarDto;
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

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    };

    @Override
    public boolean bookCar(Long carId, CarBookingDto carBookingDto) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<User> optionalUser = userRepository.findById(carBookingDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();
            User existingUser = optionalUser.get();
            CarBooking carBooking = new CarBooking();

            carBooking.setCar(existingCar);
            carBooking.setUser(existingUser);
            carBooking.setBookCarStatus(BookCarStatus.PENDING);
            carBooking.setFromDate(carBookingDto.getFromDate());
            carBooking.setToDate(carBookingDto.getToDate());

            Long diffInMilliSeconds = carBookingDto.getToDate().getTime() - carBookingDto.getFromDate().getTime();
            Long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
            carBooking.setDays(days);
            carBooking.setPrice(days * existingCar.getPrice());
            
            carBookingRepository.save(carBooking);
            return true;
        }
        return false;
    };

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDto).orElse(null);
    };

    @Override
    public List<CarBookingDto> getBookingsByUserId(Long userId) {
        return carBookingRepository.findAllByUserId(userId).stream().map(CarBooking::getCarBookingDto)
                .collect(Collectors.toList());
    };

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());
        ExampleMatcher matcher = 
            ExampleMatcher.matchingAll()
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> example = Example.of(car, matcher);
        List<Car> carList = carRepository.findAll(example);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(
            carList
                .stream()
                .map(Car::getCarDto)
                .collect(Collectors.toList())
        );
        return carDtoListDto;
    };


}
