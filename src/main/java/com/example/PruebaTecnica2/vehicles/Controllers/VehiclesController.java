package com.example.PruebaTecnica2.vehicles.Controllers;

import com.example.PruebaTecnica2.vehicles.dtos.VehicleStockRequestDto;
import com.example.PruebaTecnica2.vehicles.dtos.OperationResponseDto;
import com.example.PruebaTecnica2.vehicles.dtos.VehicleResponseDto;
import com.example.PruebaTecnica2.vehicles.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {
    
    @Autowired
    private VehiclesService vehiclesService;
    
    // ENDPOINT 1: GET /api/vehicles
    // Retorna todos los vehículos activos
    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        List<VehicleResponseDto> vehicles = vehiclesService.getAllActiveVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // ENDPOINT 2: GET /api/vehicles/low-stock-expensive
    // Retorna vehículos con price > 20000 y stock < 10
    @GetMapping("/low-stock-expensive")
    public ResponseEntity<List<VehicleResponseDto>> getLowStockExpensive() {
        List<VehicleResponseDto> vehicles = vehiclesService.getLowStockExpensiveVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // ENDPOINT 3: PATCH /api/vehicles/delete/{model}
    // Eliminación lógica de vehículos por modelo
    @PatchMapping("/delete/{model}")
    public ResponseEntity<OperationResponseDto> deleteVehicleByModel(@PathVariable String model) {
        vehiclesService.deleteVehicleByModel(model);
        return ResponseEntity.ok(new OperationResponseDto("Vehicle deleted successfully"));
    }
    
    // ENDPOINT 4: PATCH /api/vehicles/stock
    // Actualizar stock de un vehículo
    @PatchMapping("/stock")
    public ResponseEntity<VehicleResponseDto> updateStock(@RequestBody VehicleStockRequestDto request) {
        VehicleResponseDto result = vehiclesService.updateVehicleStock(request.getId(), request.getStock());
        return ResponseEntity.ok(result);
    }
}

