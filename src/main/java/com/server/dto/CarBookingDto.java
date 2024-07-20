package com.server.dto;

import java.util.Date;

import com.server.enums.BookCarStatus;

import lombok.Data;

@Data
public class CarBookingDto {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long carId;

    private Long userId;

    private String userName;

    private String email;
}
