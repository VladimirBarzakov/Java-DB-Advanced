package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.parser.ModelParserImpl;
import app.exam.parser.ValidationUtil;
import app.exam.service.api.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeesController {

    private JSONParser jsonParser;
    private EmployeeServiceImpl employeeService;
    private ValidationUtil validationUtil;

    @Autowired
    public EmployeesController(JSONParser jsonParser,
                               EmployeeServiceImpl employeeService,
                               ValidationUtil validationUtil) {
        this.jsonParser = jsonParser;
        this.employeeService = employeeService;
        this.validationUtil=validationUtil;
    }

    public String importDataFromJSON(String jsonContent){

        StringBuilder builder = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] employeeDTOs = this.jsonParser.read( EmployeeJSONImportDTO[].class, jsonContent);
            for (EmployeeJSONImportDTO employeeDTO : employeeDTOs) {
                if (this.validationUtil.isValid(employeeDTO)){
                    this.employeeService.create(employeeDTO);
                    builder.append(String.format("Record %s successfully imported.%n", employeeDTO.getName()));
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
