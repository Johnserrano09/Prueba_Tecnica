package com.example.PruebaTecnica2.vehicles.mappers;

import com.example.PruebaTecnica2.vehicles.dtos.VehicleResponseDto;
import com.example.PruebaTecnica2.vehicles.entities.vehicles;

public class VehiclesMapper {

    public static VehicleResponseDto toResponse(vehicles vehicle) {
        if (vehicle == null) {
            return null;
        }
        return new VehicleResponseDto(
            vehicle.getId(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getPrice(),
            vehicle.getStock()
        );
    }
}
