package com.example.PruebaTecnica2.vehicles.dtos;

public class VehicleStockRequestDto {
    private Long id;
    private Integer stock;
    
    public VehicleStockRequestDto() {}
    
    public VehicleStockRequestDto(Long id, Integer stock) {
        this.id = id;
        this.stock = stock;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
