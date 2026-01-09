package com.example.PruebaTecnica2.vehicles.exceptions;

public class VehicleAlreadyDeletedException extends RuntimeException {
    public VehicleAlreadyDeletedException(String message) {
        super(message);
    }
}
