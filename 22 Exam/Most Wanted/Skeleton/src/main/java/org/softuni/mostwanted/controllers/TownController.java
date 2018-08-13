package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.export.JSON.TownJSONExportDTO;
import org.softuni.mostwanted.domain.JSON.JSON.TownDTOImportJSON;
import org.softuni.mostwanted.parser.JSONParserImpl;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParserImpl;
import org.softuni.mostwanted.services.TownServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TownController {

    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private JSONParserImpl jsonParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private TownServiceImpl townService;
    @Autowired
    private XMLParserImpl xmlParser;


    public String importTowns(String read) {

        StringBuilder builder  = new StringBuilder();
        TownDTOImportJSON[] townsDTO = this.jsonParser.read(TownDTOImportJSON[].class, read);
        for (TownDTOImportJSON townDTOImportJSON : townsDTO) {
            if (this.validationUtil.isValid(townDTOImportJSON)){
                try {
                    this.townService.create(townDTOImportJSON);
                    builder.append(String.format("Succesfully imported Town – %s.%n",townDTOImportJSON.getName()));
                } catch (IllegalArgumentException e){
                    builder.append(String.format("Error: Incorrect Data!%n"));
                }
            } else {
                builder.append(String.format("Error: Incorrect Data!%n"));
            }
            
        }
        return builder.toString().trim();
    }

    public String ExportTowns() {
        List<TownJSONExportDTO> list = this.townService.getAllTownsWithRacers();

        String result = this.jsonParser.write(list);

        return result;

    }
}
