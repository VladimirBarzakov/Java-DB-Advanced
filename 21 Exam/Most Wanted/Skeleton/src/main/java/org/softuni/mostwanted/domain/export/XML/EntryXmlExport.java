package org.softuni.mostwanted.domain.export.XML;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryXmlExport {

    @XmlElement(name = "finish-time")
    private Double finishTime;

    @XmlElement
    private String car;

    public EntryXmlExport() {
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
