package car_dealer.entity.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerListBindingModel implements Serializable{

    @XmlElement(name = "customer")
    private List<CustomerBindingModel> customers;

    public CustomerListBindingModel() {
    }

    public List<CustomerBindingModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerBindingModel> customers) {
        this.customers = customers;
    }
}
