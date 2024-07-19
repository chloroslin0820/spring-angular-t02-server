package com.server.entity;

import java.util.Date;

import com.server.dto.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDto getCarDto() {
        CarDto carDto = new CarDto();
        carDto.setId(this.id);
        carDto.setBrand(this.brand);
        carDto.setColor(this.color);
        carDto.setName(this.name);
        carDto.setType(this.type);
        carDto.setTransmission(this.transmission);
        carDto.setDescription(this.description);
        carDto.setPrice(this.price);
        carDto.setYear(this.year);
        carDto.setReturnImage(image);
        return carDto;
    }
}
