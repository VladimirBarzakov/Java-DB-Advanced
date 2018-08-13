package judgeSystem.domain.dto.submissionImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "submissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionWrapperXML {

    @XmlElement(name = "submission")
    private List<SubmissionImportXML> submissions;

    public SubmissionWrapperXML() {
        this.submissions=new ArrayList<>();
    }

    public List<SubmissionImportXML> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionImportXML> submissions) {
        this.submissions = submissions;
    }
}
