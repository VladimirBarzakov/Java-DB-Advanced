package org.softuni.mostwanted.controllers;


import org.softuni.mostwanted.domain.export.XML.MostWantedXMLExport;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParserImpl;
import org.softuni.mostwanted.services.RaceEntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RaceEntryController {

    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private XMLParserImpl xmlParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private RaceEntryServiceImpl raceEntryService;

    public String create(String read) {
        StringBuilder builder  = new StringBuilder();
        org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImportWrapper raceEntieesDTO = null;
        try {
            raceEntieesDTO = this.xmlParser.read(org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImportWrapper.class, read);
        } catch (IOException | JAXBException e) {
            return null;
        }
        List<org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport> raceEntryDTOs = raceEntieesDTO.getRaceEntrees();
        for (org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport dto : raceEntryDTOs) {
            if (this.validationUtil.isValid(dto)){
                try{
                    int id = this.raceEntryService.create(dto);
                    builder.append(String.format("Succesfully imported RaceEntry – %d.%n",id));
                } catch (IllegalArgumentException e){
                    builder.append(String.format("Error: Incorrect Data!%n"));
                }
            } else {
                builder.append(String.format("Error: Incorrect Data!%n"));
            }

        }
        return builder.toString().trim();
    }

    public String getMostWanted() {

        MostWantedXMLExport dto = this.raceEntryService.getMostWanted();

        String result = "";

        try {
            result = this.xmlParser.write(dto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }
}
