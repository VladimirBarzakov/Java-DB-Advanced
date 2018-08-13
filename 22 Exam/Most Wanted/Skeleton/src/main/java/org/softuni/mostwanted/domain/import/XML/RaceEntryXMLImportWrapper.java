package org.softuni.mostwanted.domain.JSON.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXMLImportWrapper {

    @XmlElement(name = "race-entry")
    private List<org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport> raceEntrees;

    public RaceEntryXMLImportWrapper() {
        this.raceEntrees=new ArrayList<>();
    }

    public List<org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport> getRaceEntrees() {
        return raceEntrees;
    }

    public void setRaceEntrees(List<org.softuni.mostwanted.domain.JSON.XML.RaceEntryXMLImport> raceEntrees) {
        this.raceEntrees = raceEntrees;
    }
}
