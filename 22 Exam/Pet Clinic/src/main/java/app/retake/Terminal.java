package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private FileIO fileIO;

    @Autowired
    private AnimalAidController animalAidController;

    @Autowired
    private AnimalController animalController;

    @Autowired
    private ProcedureController procedureController;

    @Autowired
    private VetController vetController;

    @Autowired
    private ConsoleIO consoleIO;

    @Override
    public void run(String... strings) throws Exception {

        this.consoleIO.write(this.animalAidController.importDataFromJSON(fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
        this.consoleIO.write(this.animalController.importDataFromJSON(this.fileIO.read(Config.ANIMALS_IMPORT_JSON)));
        this.consoleIO.write(this.vetController.importDataFromXML(this.fileIO.read(Config.VETS_IMPORT_XML)));
        this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIO.read(Config.PROCEDURES_IMPORT_XML)));
        //this.consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
        this.consoleIO.write(this.procedureController.exportProcedures());
    }
}
