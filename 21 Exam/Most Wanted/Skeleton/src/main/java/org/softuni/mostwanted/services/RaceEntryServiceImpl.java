package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.entity.Car;
import org.softuni.mostwanted.domain.entity.RaceEntry;
import org.softuni.mostwanted.domain.entity.Racer;
import org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport;
import org.softuni.mostwanted.domain.export.XML.EntryXmlExport;
import org.softuni.mostwanted.domain.export.XML.MostWantedXMLExport;
import org.softuni.mostwanted.domain.export.XML.RacerXmlExport;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaceEntryServiceImpl {

    @Autowired
    private ModelParserImpl modelParser;


    @Autowired
    private RacerRepository racerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RaceEntryRepository raceEntryRepository;

    public int create(RaceEntryXMLImport dto) {

        RaceEntry raceEntry = this.modelParser.convert(dto, RaceEntry.class);
        Racer racer = this.racerRepository.findOneByName(dto.getRacer());

        Car car = this.carRepository.findById(dto.getCarId());

        if ((racer==null && dto.getRacer()!=null)||(car==null)){
            throw  new IllegalArgumentException();
        }

        String debug="";
        raceEntry.setCar(car);
        raceEntry.setRacer(racer);


        raceEntry=this.raceEntryRepository.save(raceEntry);
        return raceEntry.getId();
    }

    public MostWantedXMLExport getMostWanted() {

        List<Racer> racers = this.raceEntryRepository.GetMostWanted();
        if (racers.size()==0){
            return null;
        }
        Racer racer = racers.get(0);
        List<RaceEntry> entries = this.raceEntryRepository.getAllByRacer(racer)
                .stream()
                .sorted((x,y)->x.getFinishTime().compareTo(y.getFinishTime()))
                .collect(Collectors.toList());

        List<EntryXmlExport> entriesDTO = new ArrayList<>();
        for (RaceEntry entry : entries) {
            String car = String.format("%s %s @ %d",entry.getCar().getBrand(),
                    entry.getCar().getModel(),
                    entry.getCar().getYearOfProduction());
            EntryXmlExport export = this.modelParser.convert(entry,EntryXmlExport.class);
            export.setCar(car);
            entriesDTO.add(export);
        }
        RacerXmlExport racerDTO = this.modelParser.convert(racer, RacerXmlExport.class);
        racerDTO.setEntries(entriesDTO);
        MostWantedXMLExport wrapper = new MostWantedXMLExport();
        wrapper.setRacer(racerDTO);
        String debug = "";

        return wrapper;

    }
}
