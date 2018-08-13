package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.JSON.XML.RaceXMLImport;
import org.softuni.mostwanted.domain.JSON.XML.RaceXMLImportWrapper;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParserImpl;
import org.softuni.mostwanted.services.RaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RaceController {

    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private XMLParserImpl xmlParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private RaceServiceImpl raceService;

    public String create(String raceImportXML) {
        StringBuilder builder  = new StringBuilder();
        RaceXMLImportWrapper racesDTO = null;

        try {
            racesDTO = this.xmlParser.read(RaceXMLImportWrapper.class, raceImportXML);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
        List<RaceXMLImport> racesDTOs = racesDTO.getRaces();
        for (RaceXMLImport dto : racesDTOs) {
            if (this.validationUtil.isValid(dto)){
                try{
                    int id = this.raceService.create(dto);
                    builder.append(String.format("Succesfully imported Race – %d.%n",id));
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
