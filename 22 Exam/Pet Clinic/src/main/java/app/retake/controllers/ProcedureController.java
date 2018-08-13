package app.retake.controllers;

import app.retake.domain.dto.*;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import app.retake.services.impl.ProcedureServiceImpl;
import app.retake.services.impl.VetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class ProcedureController {

    private ProcedureServiceImpl procedureService;
    private Parser parser;

    @Autowired
    public ProcedureController(ProcedureServiceImpl procedureService,
                               @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder builder  = new StringBuilder();
        try {
            List<ProcedureXMLImportDTO> proceduresDTOs = this.parser.read(ProcedureWrapperXMLImportDTO.class,xmlContent).getProcedures();
            for (ProcedureXMLImportDTO proceduresDTO : proceduresDTOs) {

                    try{
                        this.procedureService.create(proceduresDTO);
                        builder.append(String.format("Record successfully imported.%n"));
                    } catch (IllegalArgumentException e){
                        builder.append(String.format("Error: Invalid data.%n"));
                    }
            }
        } catch (JAXBException | IOException |ParseException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }

    public String exportProcedures() throws IOException, JAXBException {

        ProcedureWrapperXMLExportDTO procedureWrapperXMLExportDTO = this.procedureService.exportProcedures();


        return this.parser.write(procedureWrapperXMLExportDTO);
    }
}
