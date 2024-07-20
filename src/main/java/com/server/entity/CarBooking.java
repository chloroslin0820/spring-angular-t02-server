package com.server.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.dto.CarBookingDto;
import com.server.enums.BookCarStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CarBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    public CarBookingDto getCarBookingDto() {
        CarBookingDto carBookingDto = new CarBookingDto();
        carBookingDto.setId(id);
        carBookingDto.setFromDate(fromDate);
        carBookingDto.setToDate(toDate);
        carBookingDto.setDays(days);
        carBookingDto.setPrice(price);
        carBookingDto.setBookCarStatus(bookCarStatus);
        carBookingDto.setCarId(car.getId());
        carBookingDto.setUserId(user.getId());
        carBookingDto.setUserName(user.getName());
        carBookingDto.setEmail(user.getEmail());
        return carBookingDto;
    }
}
