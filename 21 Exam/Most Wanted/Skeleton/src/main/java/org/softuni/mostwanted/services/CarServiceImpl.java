package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.Car;
import org.softuni.mostwanted.domain.entity.Racer;
import org.softuni.mostwanted.domain.JSON.JSON.CarDTOImportJSON;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RacerRepository racerRepository;

    @Autowired
    private ModelParserImpl modelParser;

    public void create(CarDTOImportJSON dto) {

        Racer racer = this.racerRepository.findOneByName(dto.getRacerName());
        Car car =this.carRepository.findOneByBrandAndModelAndPriceAndYearOfProductionAndMaxSpeedAndZeroToSixtyAndRacer(
                dto.getBrand(),dto.getModel(),dto.getPrice(),dto.getYearOfProduction(),dto.getMaxSpeed(),dto.getZeroToSixty(),racer);
        if (racer==null && dto.getRacerName()!=null && car!=null){
            throw  new IllegalArgumentException();
        }
        car = this.modelParser.convert(dto, Car.class);
        car.setRacer(racer);

        car = this.carRepository.save(car);
        racer.getCars().add(car);
        this.racerRepository.save(racer);
    }
}
