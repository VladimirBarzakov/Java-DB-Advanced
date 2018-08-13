package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "most-popular-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostPopularItemDTO {

    @XmlElement
    private String name;

    @XmlElement(name = "total-made")
    private BigDecimal totalMade;

    @XmlElement(name = "times-sold")
    private long timesSold;


    public MostPopularItemDTO(String name, BigDecimal totalMade, long timesSold) {
        this.name = name;
        this.totalMade = totalMade;
        this.timesSold = timesSold;
    }

    public MostPopularItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalMade() {
        return totalMade;
    }

    public void setTotalMade(BigDecimal totalMade) {
        this.totalMade = totalMade;
    }

    public long getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(long timesSold) {
        this.timesSold = timesSold;
    }
}
