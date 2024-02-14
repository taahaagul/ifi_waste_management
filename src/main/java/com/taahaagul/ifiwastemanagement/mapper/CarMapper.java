package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.CarDTO;
import com.taahaagul.ifiwastemanagement.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDTO mapToCarDTO(Car car) {
        CarDTO.CarDTOBuilder builder = CarDTO.builder()
                .id(car.getId())
                .targoNo(car.getTargoNo())
                .ownerName(car.getOwnerName())
                .ownerSurname(car.getOwnerSurname())
                .ownerPhone(car.getOwnerPhone())
                .taxFee(car.getTaxFee())
                .vehicleClass(car.getVehicleClass())
                .amount(car.getAmount())
                .fuelType(car.getFuelType())
                .status(car.isStatus())
                .createdAt(car.getCreatedAt())
                .createdBy(car.getCreatedBy())
                .updatedAt(car.getUpdatedAt())
                .updatedBy(car.getUpdatedBy());

        if (car.getZone() != null) {
            builder.zoneName(car.getZone().getZoneName())
                    .zoneId(car.getZone().getId());
        }
        return builder.build();
    }

    public Car mapToCar(CarDTO carDTO, Car car) {
        car.setTargoNo(carDTO.getTargoNo());
        car.setOwnerName(carDTO.getOwnerName());
        car.setOwnerSurname(carDTO.getOwnerSurname());
        car.setOwnerPhone(carDTO.getOwnerPhone());
        car.setTaxFee(carDTO.getTaxFee());
        car.setVehicleClass(carDTO.getVehicleClass());
        car.setAmount(carDTO.getAmount());
        car.setFuelType(carDTO.getFuelType());
        car.setStatus(carDTO.isStatus());
        return car;
    }
}
