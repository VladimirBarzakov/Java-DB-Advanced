package judgeSystem.domain.dto.enrollmentInport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContestXML {

    @XmlElement
    private long id;

    public ContestXML() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
