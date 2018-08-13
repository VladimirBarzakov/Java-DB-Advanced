package org.softuni.mostwanted.domain.JSON.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImportWrapper {

    @XmlElement(name = "race")
    private List<RaceXMLImport> races;

    public RaceXMLImportWrapper() {
        this.races=new ArrayList<>();
    }

    public List<RaceXMLImport> getRaces() {
        return races;
    }

    public void setRaces(List<RaceXMLImport> races) {
        this.races = races;
    }
}
