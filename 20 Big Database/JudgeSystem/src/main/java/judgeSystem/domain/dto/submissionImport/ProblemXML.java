package judgeSystem.domain.dto.submissionImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "problem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemXML {

    @XmlElement
    private Long id;

    public ProblemXML() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
