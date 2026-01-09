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
    
    // ENDPOINT 1
    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        List<VehicleResponseDto> vehicles = vehiclesService.getAllActiveVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // ENDPOINT 2
    @GetMapping("/low-stock-expensive")
    public ResponseEntity<List<VehicleResponseDto>> getLowStockExpensive() {
        List<VehicleResponseDto> vehicles = vehiclesService.getLowStockExpensiveVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // ENDPOINT 3
    @PatchMapping("/delete/{model}")
    public ResponseEntity<?> deleteVehicleByModel(@PathVariable String model) {
        String result = vehiclesService.deleteVehicleByModel(model);
        
        if (result == null) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OperationResponseDto("El vehículo con modelo '" + model + "' no existe."));
        } else if ("already_deleted".equals(result)) {
           
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new OperationResponseDto("El vehículo con modelo '" + model + "' ya se encuentra eliminado."));
        } else {
        
            return ResponseEntity.ok(new OperationResponseDto("Vehículo con modelo '" + model + "' eliminado correctamente."));
        }
    }
    
    // ENDPOINT 4
    @PatchMapping("/stock")
    public ResponseEntity<?> updateStock(@RequestBody VehicleStockRequestDto request) {
        Optional<VehicleResponseDto> result = vehiclesService.updateVehicleStock(request.getId(), request.getStock());
        
        if (result.isEmpty()) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OperationResponseDto("El vehículo con ID " + request.getId() + " no existe."));
        } else {
          
            return ResponseEntity.ok(result.get());
        }
    }
}

