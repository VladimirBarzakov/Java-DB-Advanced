package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.JSON.JSON.CarDTOImportJSON;
import org.softuni.mostwanted.parser.JSONParserImpl;
import org.softuni.mostwanted.parser.ModelParserImpl;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarController {


    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private JSONParserImpl jsonParser;
    @Autowired
    private ModelParserImpl modelParser;
    @Autowired
    private CarServiceImpl carService;

    public String create(String read) {

        StringBuilder builder  = new StringBuilder();
        CarDTOImportJSON[] carsDTO = this.jsonParser.read(CarDTOImportJSON[].class, read);
        for (CarDTOImportJSON dto : carsDTO) {
            if (this.validationUtil.isValid(dto)){
                try{
                    this.carService.create(dto);
                    builder.append(String.format("Succesfully imported Car – %s %s @ %d.%n",
                            dto.getBrand(), dto.getModel(), dto.getYearOfProduction()));
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
