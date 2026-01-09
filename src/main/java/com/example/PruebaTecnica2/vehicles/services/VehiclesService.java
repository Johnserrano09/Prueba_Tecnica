package com.example.PruebaTecnica2.vehicles.services;

import com.example.PruebaTecnica2.vehicles.dtos.VehicleResponseDto;
import com.example.PruebaTecnica2.vehicles.entities.vehicles;
import com.example.PruebaTecnica2.vehicles.exceptions.InvalidRequestDataException;
import com.example.PruebaTecnica2.vehicles.exceptions.VehicleAlreadyDeletedException;
import com.example.PruebaTecnica2.vehicles.exceptions.VehicleNotFoundException;
import com.example.PruebaTecnica2.vehicles.mappers.VehiclesMapper;
import com.example.PruebaTecnica2.vehicles.repositories.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiclesService {
    
    @Autowired
    private VehiclesRepository vehiclesRepository;
    
    // ENDPOINT 1
    public List<VehicleResponseDto> getAllActiveVehicles() {
        List<vehicles> vehicles = vehiclesRepository.findByDeleted("N");
        return vehicles.stream()
                .map(VehiclesMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // ENDPOINT 2
    public List<VehicleResponseDto> getLowStockExpensiveVehicles() {
        List<vehicles> vehicles = vehiclesRepository.findByPriceAndStockAndDeleted(20000, 10, "N");
        return vehicles.stream()
                .map(VehiclesMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // ENDPOINT 3
    public VehicleResponseDto deleteVehicleByModel(String model) {
        List<vehicles> vehicleList = vehiclesRepository.findByModelAndDeleted(model, "N");
        
        if (vehicleList.isEmpty()) {
            List<vehicles> deletedVehicles = vehiclesRepository.findByModel(model);
            if (deletedVehicles.isEmpty()) {
                throw new VehicleNotFoundException("Vehicle not found");
            } else {
                throw new VehicleAlreadyDeletedException("Vehicle already deleted");
            }
        }
        
        for (vehicles v : vehicleList) {
            v.setDeleted("S");
            vehiclesRepository.save(v);
        }
        
        return VehiclesMapper.toResponse(vehicleList.get(0));
    }
    
    // ENDPOINT 4
    public VehicleResponseDto updateVehicleStock(Long id, Integer newStock) {
        // Validar que el id no sea nulo
        if (id == null) {
            throw new InvalidRequestDataException("Invalid request data");
        }
        
        // Validar que el stock no sea nulo y sea mayor o igual a 0
        if (newStock == null || newStock < 0) {
            throw new InvalidRequestDataException("Invalid request data");
        }
        
        Optional<vehicles> vehicle = vehiclesRepository.findById(id);
        
        if (vehicle.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle not found");
        }
        
        vehicles v = vehicle.get();
        v.setStock(newStock);
        vehiclesRepository.save(v);
        return VehiclesMapper.toResponse(v);
    }
}
