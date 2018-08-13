package car_dealer.entity.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerListViewModel implements Serializable{

    @XmlElement(name = "customer")
    private List<CustomerViewModel> customers;

    public CustomerListViewModel() {
    }

    public List<CustomerViewModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerViewModel> customers) {
        this.customers = customers;
    }
}
