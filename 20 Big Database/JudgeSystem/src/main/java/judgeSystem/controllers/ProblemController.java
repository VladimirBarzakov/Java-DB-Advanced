package judgeSystem.controllers;

import judgeSystem.domain.dto.problemImport.ProblemWrapperXML;
import judgeSystem.domain.dto.problemImport.ProblemXML;
import judgeSystem.parser.XMLParser;
import judgeSystem.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProblemController {

    private final ProblemService problemService;

    private final XMLParser xmlParser;

    @Autowired
    public ProblemController(ProblemService problemService, XMLParser xmlParser) {
        this.problemService = problemService;
        this.xmlParser = xmlParser;
    }

    public String importProblem(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        ProblemWrapperXML wrapperXML = this.xmlParser.read(
                ProblemWrapperXML.class, content);
        for (ProblemXML problemXML : wrapperXML.getProblemXMLS()) {
            builder.append(this.problemService.importDTO(problemXML));
        }
        return builder.toString().trim();
    }
}
