package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerViewModel implements Serializable{

    @XmlElement(name = "id")
    private int Id;

    @XmlElement(name = "name")
    private String Name;

    @XmlElement(name = "birth-date")
    private Date BirthDate;

    @XmlElement(name = "is-young-driver")
    private boolean IsYoungDriver;

    public OrderedCustomerViewModel() {
    }

    public OrderedCustomerViewModel(int id, String name, Date birthDate, boolean isYoungDriver) {
        this.Id = id;
        this.Name = name;
        this.BirthDate = birthDate;
        this.IsYoungDriver = isYoungDriver;
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

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }


}
