package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.export.JSON.RacerJSONExportDTO;
import org.softuni.mostwanted.domain.JSON.JSON.RacerDTOImportJSON;
import org.softuni.mostwanted.parser.JSONParserImpl;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.RacerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RacerController {

    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private JSONParserImpl jsonParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private RacerServiceImpl racerService;


    public String create(String read) {

        StringBuilder builder  = new StringBuilder();
        RacerDTOImportJSON[] rasersDTO = this.jsonParser.read(RacerDTOImportJSON[].class, read);
        for (RacerDTOImportJSON dto : rasersDTO) {
            if (this.validationUtil.isValid(dto)){
                try{
                    this.racerService.create(dto);
                    builder.append(String.format("Succesfully imported Racer – %s.%n",dto.getName()));
                } catch (IllegalArgumentException e){
                    builder.append(String.format("Error: Incorrect Data!%n"));
                }
            } else {
                builder.append(String.format("Error: Incorrect Data!%n"));
            }

        }
        return builder.toString().trim();

    }

    public String getAllWithCars() {

        List<RacerJSONExportDTO> list = this.racerService.getAllWithCars();

        String result = this.jsonParser.write(list);

        return result;
    }
}
