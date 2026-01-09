package com.example.PruebaTecnica2.vehicles.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "vehicles")
public class vehicles {

    @Id
    private long id;
    
    @Column(name = "brand", nullable = false)
    private String brand;
    
    @Column(name = "model", nullable = false)
    private String model;
    
    @Column(name = "price", nullable = false)
    private double price;
    
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    @Column(name = "deleted", nullable = false)
    private String deleted;

    public vehicles() {}

    public vehicles(long id, String brand, String model, double price, Integer stock, String deleted) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
        this.deleted = deleted;
    }

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public double getPrice() {
        return price;
    }
    public Integer getStock() {
        return stock;
    }
    public String getDeleted() {
        return deleted;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }



}
