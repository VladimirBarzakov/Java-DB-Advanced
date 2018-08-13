package org.softuni.mostwanted.domain.JSON.XML;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryXmlImport {

    @XmlAttribute
    private Integer id;

    public EntryXmlImport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
