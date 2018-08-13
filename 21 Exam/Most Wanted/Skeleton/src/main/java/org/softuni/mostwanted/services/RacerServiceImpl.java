package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.Car;
import org.softuni.mostwanted.domain.entity.Racer;
import org.softuni.mostwanted.domain.entity.Town;
import org.softuni.mostwanted.domain.export.JSON.RacerJSONExportDTO;
import org.softuni.mostwanted.domain.JSON.JSON.RacerDTOImportJSON;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl {

    @Autowired
    private ModelParserImpl modelParser;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private RacerRepository racerRepository;


    public void create(RacerDTOImportJSON dto) {
        Town town = this.townRepository.findOneByName(dto.getHomeTown());

        if (town==null && dto.getHomeTown()!=null){
            throw  new IllegalArgumentException();
        }
        Racer racer= this.racerRepository.findOneByNameAndAgeAndBountyAndHomeTown(
                dto.getName(),
                dto.getAge(),
                dto.getBounty(),
                town);
        if (racer!=null){
            throw  new IllegalArgumentException();
        }
        racer = this.modelParser.convert(dto, Racer.class);

        racer.setHomeTown(town);
        this.racerRepository.save(racer);
    }

    public List<RacerJSONExportDTO> getAllWithCars() {

        List<Racer> racers = this.racerRepository.findAll().stream().sorted((x,y)->{
            int comparator = Integer.compare(y.getCars().size(),x.getCars().size());
            if (comparator==0){
                comparator=x.getName().compareTo(y.getName());
            }
            return comparator;
        }).collect(Collectors.toList());
        List<RacerJSONExportDTO> result= new ArrayList<>();
        for (Racer racer : racers) {
            if (racer.getName()==null){
                continue;
            }
            RacerJSONExportDTO racerDto = new RacerJSONExportDTO();
            racerDto.setName(racer.getName());
            racerDto.setAge(racer.getAge());
            Set<Car> cars = racer.getCars();
            for (Car car : cars) {
                racerDto.getCars().add(String.format("%s %s %d",
                        car.getBrand(),
                        car.getModel(),
                        car.getYearOfProduction()));
            }
            result.add(racerDto);
        }

        return result;
    }
}
