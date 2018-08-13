package org.softuni.mostwanted.domain.export.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedXMLExport {

    @XmlElement(name = "racer")
    private RacerXmlExport racer;

    public MostWantedXMLExport() {
    }

    public RacerXmlExport getRacer() {
        return racer;
    }

    public void setRacer(RacerXmlExport racer) {
        this.racer = racer;
    }
}
