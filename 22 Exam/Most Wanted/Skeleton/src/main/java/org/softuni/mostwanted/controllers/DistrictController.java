package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.JSON.JSON.DistrictDTOImportJSON;
import org.softuni.mostwanted.parser.JSONParserImpl;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DistrictController {


    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private JSONParserImpl jsonParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private DistrictServiceImpl districtService;


    public String create(String read) {
        StringBuilder builder  = new StringBuilder();
        DistrictDTOImportJSON[] districtsDTO = this.jsonParser.read(DistrictDTOImportJSON[].class, read);
        for (DistrictDTOImportJSON districtDTOImportJSON : districtsDTO) {
            if (this.validationUtil.isValid(districtDTOImportJSON)){
                try{
                    this.districtService.create(districtDTOImportJSON);
                    builder.append(String.format("Succesfully imported District – %s.%n",districtDTOImportJSON.getName()));
                } catch (IllegalArgumentException e){
                    builder.append(String.format("Error: Incorrect Data!%n"));
                }
            } else {
                builder.append(String.format("Error: Incorrect Data!%n"));
            }

        }
        return builder.toString().trim();
    }
}
