package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CarDTO;
import com.taahaagul.ifiwastemanagement.dto.UserDTO;
import com.taahaagul.ifiwastemanagement.entity.Car;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.entity.Zone;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.mapper.CarMapper;
import com.taahaagul.ifiwastemanagement.mapper.UserMapper;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.repository.ZoneRepository;
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
    private final CarMapper carMapper;
    private final UserMapper userMapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarDTO createCar(CarDTO carDTO) {
        Zone foundedZone = null;
        if (carDTO.getZoneId() != null) {
            foundedZone = zoneRepository.findById(carDTO.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", carDTO.getZoneId().toString()));
        }

        Car savedCar = carMapper.mapToCar(carDTO, new Car());
        savedCar.setZone(foundedZone);
        return carMapper.mapToCarDTO(carRepository.save(savedCar));
    }

    public CarDTO updateCar(CarDTO carDTO) {
        Car foundedCar = carRepository.findById(carDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carDTO.getId().toString()));

        carMapper.mapToCar(carDTO, foundedCar);

        return carMapper.mapToCarDTO(carRepository.save(foundedCar));
    }

    public void updateCarZone(Long carId, Long zoneId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        Zone foundedZone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "zoneId", zoneId.toString()));

        foundedCar.setZone(foundedZone);
        carRepository.save(foundedCar);
    }

    public Page<CarDTO> getAllCar(Pageable pageable) {
        Page<Car> cars = carRepository.findAll(pageable);

        return cars.map(carMapper::mapToCarDTO);
    }

    @Transactional
    public void deleteCar(Long carId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        userRepository.removeCarFromUsers(carId);

        carRepository.delete(foundedCar);
    }

    public List<UserDTO> getCarUsers(Long carId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));
        List<User> users = foundedCar.getUsers();

        return users.stream()
                .map(userMapper::mapToUserDTO)
                .toList();
    }

    public CarDTO getAnyCar(Long carId) {
        Car foundedCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        return carMapper.mapToCarDTO(foundedCar);
    }
}