package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class AnimalController {

    private AnimalService animalService;
    private Parser parser;

    @Autowired
    public AnimalController(AnimalService animalService,
                            @Qualifier("JSONParser") Parser parser) {
        this.animalService = animalService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {

        StringBuilder builder  = new StringBuilder();
        try {
            AnimalJSONImportDTO[] animalsDTOs = this.parser.read(AnimalJSONImportDTO[].class,jsonContent);
            for (AnimalJSONImportDTO animalDTO : animalsDTOs) {

                if (animalDTO!=null && ValidationUtil.isValid(animalDTO) &&
                        animalDTO.getPassport()!= null && ValidationUtil.isValid(animalDTO.getPassport())) {
                    try{
                        this.animalService.create(animalDTO);
                        builder.append(String.format("Record %s Passport №: %s successfully imported.%n",
                                animalDTO.getName(),
                                animalDTO.getPassport().getSerialNumber()));
                    } catch (IllegalArgumentException e){
                        builder.append(String.format("Error: Invalid data.%n"));
                    }

                } else{
                    builder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException| IOException | ParseException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().setDateFormat("dd-MMM-yyyy")
                .create();

        List<AnimalsJSONExportDTO> ownedAnimal = this.animalService.findByOwnerPhoneNumber(phoneNumber);

        return gson.toJson(ownedAnimal);
    }
}
