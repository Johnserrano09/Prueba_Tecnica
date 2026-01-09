package com.example.PruebaTecnica2.vehicles.Controllers;

import com.example.PruebaTecnica2.vehicles.dtos.VehicleStockRequestDto;
import com.example.PruebaTecnica2.vehicles.dtos.OperationResponseDto;
import com.example.PruebaTecnica2.vehicles.dtos.VehicleResponseDto;
import com.example.PruebaTecnica2.vehicles.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> deleteVehicleByModel(@PathVariable String model) {
        String result = vehiclesService.deleteVehicleByModel(model);
        
        if (result == null) {
            // Vehículo no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OperationResponseDto("El vehículo con modelo '" + model + "' no existe."));
        } else if ("already_deleted".equals(result)) {
            // Vehículo ya estaba eliminado
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new OperationResponseDto("El vehículo con modelo '" + model + "' ya se encuentra eliminado."));
        } else {
            // Vehículo eliminado exitosamente
            return ResponseEntity.ok(new OperationResponseDto("Vehículo con modelo '" + model + "' eliminado correctamente."));
        }
    }
    
    // ENDPOINT 4: PATCH /api/vehicles/stock
    // Actualizar stock de un vehículo
    @PatchMapping("/stock")
    public ResponseEntity<?> updateStock(@RequestBody VehicleStockRequestDto request) {
        Optional<VehicleResponseDto> result = vehiclesService.updateVehicleStock(request.getId(), request.getStock());
        
        if (result.isEmpty()) {
            // Vehículo no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OperationResponseDto("El vehículo con ID " + request.getId() + " no existe."));
        } else {
            // Vehículo actualizado exitosamente
            return ResponseEntity.ok(result.get());
        }
    }
}

