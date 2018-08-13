package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.impl.ConsoleIOImpl;
import org.softuni.mostwanted.io.impl.FileIOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    @Autowired
    public Terminal() { }

    @Autowired
    private ConsoleIOImpl consoleIO;

    @Autowired
    private FileIOImpl fileIO;
    @Autowired
    private TownController townController;

    @Autowired
    private DistrictController districtController;

    @Autowired
    private RacerController racerController;

    @Autowired
    private CarController carController;

    @Autowired
    private RaceEntryController raceEntryController;

    @Autowired
    private RaceController raceController;

    @Override
    public void run(String... args) throws Exception {

        //this.consoleIO.write(this.townController.importTowns(this.fileIO.read(Config.TOWNS_IMPORT_JASON)));
        //this.consoleIO.write(this.districtController.create(this.fileIO.read(Config.DISTRICTS_IMPORT_JASON)));
        //this.consoleIO.write(this.racerController.create(this.fileIO.read(Config.RACERS_IMPORT_JASON)));
        //this.consoleIO.write(this.carController.create(this.fileIO.read(Config.CARS_IMPORT_JASON)));
        //this.consoleIO.write(this.raceEntryController.create(this.fileIO.read(Config.RACE_ENRIES_IMPORT_JASON)));
        //this.consoleIO.write(this.raceController.create(this.fileIO.read(Config.RACE_IMPORT_XML)));
        //this.fileIO.write(this.townController.ExportTowns(),"racingTowns.json");
        //this.fileIO.write(this.racerController.getAllWithCars(),"racingCars.json");
        //this.fileIO.write(this.raceEntryController.getMostWanted(),"most-wanted.xml");

    }
}
