package com.example.PruebaTecnica2.vehicles.services;

import com.example.PruebaTecnica2.vehicles.dtos.VehicleResponseDto;
import com.example.PruebaTecnica2.vehicles.entities.vehicles;
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
    public String deleteVehicleByModel(String model) {
        List<vehicles> vehicleList = vehiclesRepository.findByModelAndDeleted(model, "N");
        
        if (vehicleList.isEmpty()) {
            
            List<vehicles> deletedVehicles = vehiclesRepository.findByModel(model);
            if (deletedVehicles.isEmpty()) {
                return null; 
            } else {
                return "already_deleted"; 
            }
        }
        
      
        for (vehicles v : vehicleList) {
            v.setDeleted("S");
            vehiclesRepository.save(v);
        }
        return "deleted";
    }
    
    // ENDPOINT 4
    public Optional<VehicleResponseDto> updateVehicleStock(long id, int newStock) {
        Optional<vehicles> vehicle = vehiclesRepository.findById(id);
        
        if (vehicle.isEmpty()) {
            return Optional.empty();
        }
        
        vehicles v = vehicle.get();
        v.setStock(newStock);
        vehiclesRepository.save(v);
        return Optional.of(VehiclesMapper.toResponse(v));
    }
}
