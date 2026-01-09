package com.example.PruebaTecnica2.vehicles.mappers;

import com.example.PruebaTecnica2.vehicles.dtos.vehiclesResponseDto;
import com.example.PruebaTecnica2.vehicles.entities.vehicles;

public class VehiclesMapper {

    public static vehiclesResponseDto toResponse(vehicles vehicle) {
        if (vehicle == null) {
            return null;
        }
        return new vehiclesResponseDto(
            vehicle.getId(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getPrice(),
            vehicle.getStock()
        );
    }
}
