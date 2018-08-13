package judgeSystem.domain.dto.problemImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "problems")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProblemWrapperXML {

    @XmlElement(name = "problem")
    private List<ProblemXML> problemXMLS;

    public ProblemWrapperXML() {
        this.problemXMLS=new ArrayList<>();
    }

    public List<ProblemXML> getProblemXMLS() {
        return problemXMLS;
    }

    public void setProblemXMLS(List<ProblemXML> problemXMLS) {
        this.problemXMLS = problemXMLS;
    }
}
