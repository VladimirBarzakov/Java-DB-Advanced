package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.impl.VetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class VetController {

    private VetServiceImpl vetService;
    private Parser parser;

    @Autowired
    public VetController(VetServiceImpl vetService,
                         @Qualifier("XMLParser") Parser parser) {
        this.vetService = vetService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent){

        StringBuilder builder  = new StringBuilder();
        try {
            List<VetXMLImportDTO> vetDTOs = this.parser.read(VetWrapperXMLImportDTO.class,xmlContent).getVets();
            for (VetXMLImportDTO vetDTO : vetDTOs) {

                if (vetDTO!=null && ValidationUtil.isValid(vetDTO)) {
                    try{
                        this.vetService.create(vetDTO);
                        builder.append(String.format("Record %s successfully imported.%n",vetDTO.getName()));
                    } catch (IllegalArgumentException e){
                        builder.append(String.format("Error: Invalid data.%n"));
                    }

                } else{
                    builder.append(String.format("Error: Invalid data.%n"));
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();

    }
}
