package car_dealer.entity.dto.view;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierViewModel implements Serializable{

    @XmlAttribute(name = "id")
    private int Id;
    @XmlAttribute(name = "name")
    private String Name;
    @XmlAttribute(name = "parts-count")
    private long partCount;

    public SupplierViewModel() {
    }

    public SupplierViewModel(int id, String name, long partCount) {
        Id = id;
        Name = name;
        this.partCount = partCount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getPartCount() {
        return partCount;
    }

    public void setPartCount(long partCount) {
        this.partCount = partCount;
    }
}
