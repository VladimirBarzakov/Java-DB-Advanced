package org.softuni.mostwanted.domain.export.XML;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerXmlExport {

    @XmlAttribute
    private String name;


    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryXmlExport> entries;

    public RacerXmlExport() {
        this.entries=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntryXmlExport> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryXmlExport> entries) {
        this.entries = entries;
    }
}
