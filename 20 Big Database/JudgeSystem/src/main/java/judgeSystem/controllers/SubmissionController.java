package judgeSystem.controllers;

import judgeSystem.domain.dto.submissionImport.SubmissionImportXML;
import judgeSystem.domain.dto.submissionImport.SubmissionWrapperXML;
import judgeSystem.parser.XMLParser;
import judgeSystem.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SubmissionController {

    private final SubmissionService submissionService;

    private final XMLParser xmlParser;

    @Autowired
    public SubmissionController(SubmissionService submissionService,
                                XMLParser xmlParser) {
        this.submissionService = submissionService;
        this.xmlParser = xmlParser;
    }

    public String importSubmission(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        SubmissionWrapperXML wrapperXML = this.xmlParser.read(
                SubmissionWrapperXML.class, content);
        for (SubmissionImportXML submissionImportXML : wrapperXML.getSubmissions()) {
            builder.append(this.submissionService.importDTO(submissionImportXML));
        }
        return builder.toString().trim();
    }
}
