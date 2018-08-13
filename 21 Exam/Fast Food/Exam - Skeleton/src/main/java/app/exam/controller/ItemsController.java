package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.ValidationUtil;
import app.exam.service.api.ItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController{

    private JSONParser jsonParser;
    private ItemsServiceImpl itemsService;
    private ValidationUtil validationUtil;

    @Autowired
    public ItemsController(JSONParser jsonParser,
                           ItemsServiceImpl itemsService,
                           ValidationUtil validationUtil) {
        this.jsonParser = jsonParser;
        this.itemsService = itemsService;
        this.validationUtil = validationUtil;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder builder = new StringBuilder();
        try {
            ItemJSONImportDTO[] itemsDTOs = this.jsonParser.read( ItemJSONImportDTO[].class, jsonContent);
            for (ItemJSONImportDTO itemDTO : itemsDTOs) {
                if (this.validationUtil.isValid(itemDTO)){
                    try {
                        this.itemsService.create(itemDTO);
                        builder.append(String.format("Record %s successfully imported.%n", itemDTO.getName()));
                    } catch (IllegalArgumentException e){
                        builder.append(String.format("Error: Invalid data.%n"));
                    }
                } else {
                    builder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }



        return builder.toString().trim();
    }
}
