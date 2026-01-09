package com.example.PruebaTecnica2.vehicles.repositories;

import com.example.PruebaTecnica2.vehicles.entities.vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiclesRepository extends JpaRepository<vehicles, Long> {
    
    List<vehicles> findByBrandAndDeleted(String brand, String deleted);

    List<vehicles> findByModelAndDeleted(String model, String deleted);
    
    List<vehicles> findByModel(String model);
 
    List<vehicles> findByDeleted(String deleted);
    
    @Query("SELECT v FROM vehicles v WHERE v.price > :price AND v.stock < :stock AND v.deleted = :deleted")
    List<vehicles> findByPriceAndStockAndDeleted(@Param("price") double price, @Param("stock") int stock, @Param("deleted") String deleted);
    
    @Query("SELECT v FROM vehicles v WHERE v.id = :id AND v.deleted = :deleted")
    Optional<vehicles> findByIdAndDeleted(@Param("id") long id, @Param("deleted") String deleted);
}
