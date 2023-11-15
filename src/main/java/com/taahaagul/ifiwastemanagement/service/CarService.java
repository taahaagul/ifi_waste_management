package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Car;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.CarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final ZoneRepository zoneRepository;
    private final CarRepository carRepository;

    public void createCar(CarRequest carRequest) {
        Zone foundedZone = null;
        if (carRequest.getZoneId() != null)
            foundedZone = zoneRepository.findById(carRequest.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", carRequest.getZoneId().toString()));

        Car car = Car.builder()
                .zone(foundedZone)
                .targoNo(carRequest.getTargoNo())
                .ownerName(carRequest.getOwnerName())
                .ownerSurname(carRequest.getOwnerSurname())
                .ownerPhone(carRequest.getOwnerPhone())
                .taxFee(carRequest.getTaxFee())
                .vehicleClass(carRequest.getVehicleClass())
                .amount(carRequest.getAmount())
                .fuelType(carRequest.getFuelType())
                .status(carRequest.isStatus())
                .build();

        carRepository.save(car);
    }

    public void updateCarZone(Long carId, Long zoneId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCar.setZone(foundedZone);
        carRepository.save(foundedCar);
    }
}