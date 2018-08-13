package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entity.Car;
import org.softuni.mostwanted.domain.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

    Car findOneByBrandAndModelAndPriceAndYearOfProductionAndMaxSpeedAndZeroToSixtyAndRacer(
            String brand, String model, BigDecimal price, Integer yearOfProduction, Double maxSpeed,
            Double zeroToSixty, Racer racer);

    Car findById(int id);
}
