package judgeSystem.domain.dto.problemImport;

import judgeSystem.domain.dto.enrollmentInport.ContestXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "problem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemXML {

    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private ContestXML contest;

    public ProblemXML() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestXML getContest() {
        return contest;
    }

    public void setContest(ContestXML contest) {
        this.contest = contest;
    }
}
