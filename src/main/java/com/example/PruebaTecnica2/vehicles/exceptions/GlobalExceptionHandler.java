package com.example.PruebaTecnica2.vehicles.exceptions;

import com.example.PruebaTecnica2.vehicles.dtos.OperationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<OperationResponseDto> handleVehicleNotFound(VehicleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new OperationResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(InvalidRequestDataException.class)
    public ResponseEntity<OperationResponseDto> handleInvalidRequestData(InvalidRequestDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new OperationResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(VehicleAlreadyDeletedException.class)
    public ResponseEntity<OperationResponseDto> handleVehicleAlreadyDeleted(VehicleAlreadyDeletedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new OperationResponseDto(ex.getMessage()));
    }
}
