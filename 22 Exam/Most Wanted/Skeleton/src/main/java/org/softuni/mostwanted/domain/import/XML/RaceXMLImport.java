package org.softuni.mostwanted.domain.JSON.XML;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImport {

    @XmlElement
    private int laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryXmlImport> entrees;

    public RaceXMLImport() {
        this.entrees = new ArrayList<>();
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryXmlImport> getEntrees() {
        return entrees;
    }

    public void setEntrees(List<EntryXmlImport> entrees) {
        this.entrees = entrees;
    }
}
