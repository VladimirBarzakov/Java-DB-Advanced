package judgeSystem.controllers;

import judgeSystem.domain.dto.contetsImport.ContestImportJSON;
import judgeSystem.domain.dto.enrollmentInport.ParticipationWrapperXML;
import judgeSystem.domain.dto.enrollmentInport.ParticipationXML;
import judgeSystem.parser.JsonParser;
import judgeSystem.parser.MyException;
import judgeSystem.parser.XMLParser;
import judgeSystem.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContestController {

    private final ContestService contestService;

    private final JsonParser jsonParser;

    private final XMLParser xmlParser;

    @Autowired
    public ContestController(ContestService contestService,
                             JsonParser jsonParser,
                             XMLParser xmlParser) {
        this.contestService = contestService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String insertContest(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        ContestImportJSON[] dtoS = this.jsonParser.read(
                ContestImportJSON[].class, content);
        for (ContestImportJSON dto : dtoS) {
            try {
                this.contestService.importDTO(dto);
                builder.append(String.format("Added contest %s.%n",dto.getName()));
            } catch (MyException e){
                builder.append(String.format("Invalid data%n"));
            }
        }
        return builder.toString().trim();
    }

    public String enrollUser(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        ParticipationWrapperXML wrapperXML = this.xmlParser.read(
                ParticipationWrapperXML.class,
                content);
        for (ParticipationXML participationXML : wrapperXML.getParticipations()) {
            builder.append(this.contestService.importParticipation(participationXML));
        }
        return builder.toString().trim();
    }
}
