package com.example.PruebaTecnica2.vehicles.dtos;

public class vehiclesResponseDto {
    
    private long id;
    private String brand;
    private String model;
    private double price;
    private Integer stock;
    
    public vehiclesResponseDto() {}
    
    public vehiclesResponseDto(long id, String brand, String model, double price, Integer stock) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
