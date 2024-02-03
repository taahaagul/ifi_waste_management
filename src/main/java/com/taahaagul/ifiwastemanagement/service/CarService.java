package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Car;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
import com.taahaagul.ifiwastemanagement.request.CarRequest;
import com.taahaagul.ifiwastemanagement.request.CarUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.CarResponse;
import com.taahaagul.ifiwastemanagement.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final ZoneRepository zoneRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public void createCar(CarRequest carRequest) {
        Zone foundedZone = null;
        if (carRequest.getZoneId() != null) {
            foundedZone = zoneRepository.findById(carRequest.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", carRequest.getZoneId().toString()));
        }

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

    public CarResponse updateCar(Long carId, CarUpdateRequest carUpdateRequest) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        foundedCar.setTargoNo(carUpdateRequest.getTargoNo());
        foundedCar.setOwnerName(carUpdateRequest.getOwnerName());
        foundedCar.setOwnerSurname(carUpdateRequest.getOwnerSurname());
        foundedCar.setOwnerPhone(carUpdateRequest.getOwnerPhone());
        foundedCar.setTaxFee(carUpdateRequest.getTaxFee());
        foundedCar.setVehicleClass(carUpdateRequest.getVehicleClass());
        foundedCar.setAmount(carUpdateRequest.getAmount());
        foundedCar.setFuelType(carUpdateRequest.getFuelType());
        foundedCar.setStatus(carUpdateRequest.isStatus());

        return new CarResponse(carRepository.save(foundedCar));
    }

    public void updateCarZone(Long carId, Long zoneId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCar.setZone(foundedZone);
        carRepository.save(foundedCar);
    }

    public Page<CarResponse> getAllCar(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(CarResponse::new);
    }

    @Transactional
    public void deleteCar(Long carId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        userRepository.removeCarFromUsers(carId);

        carRepository.delete(foundedCar);
    }

    public List<UserResponse> getCarUsers(Long carId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        return foundedCar.getUsers()
                .stream()
                .map(UserResponse::new)
                .toList();
    }
}