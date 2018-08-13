package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.impl.AnimalAidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private AnimalAidServiceImpl aidService;
    private Parser parser;

    @Autowired
    public AnimalAidController(AnimalAidServiceImpl aidService,
                             @Qualifier("JSONParser") Parser parser) {
        this.aidService = aidService;
        this.parser=parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {

        StringBuilder builder  = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] animalAidDTOs = this.parser.read(AnimalAidJSONImportDTO[].class,jsonContent);
            for (AnimalAidJSONImportDTO animalAidDTO : animalAidDTOs) {

                if (animalAidDTO!=null && ValidationUtil.isValid(animalAidDTO)) {
                    try{
                        this.aidService.create(animalAidDTO);
                        builder.append(String.format("Record %s successfully imported.%n", animalAidDTO.getName()));
                    } catch (IllegalArgumentException e){
                        builder.append(String.format("Error: Invalid data.%n"));
                    }

                } else{
                    builder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
