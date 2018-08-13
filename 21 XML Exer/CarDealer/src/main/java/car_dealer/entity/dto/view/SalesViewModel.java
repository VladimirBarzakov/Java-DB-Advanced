package car_dealer.entity.dto.view;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesViewModel implements Serializable{

    @XmlElement
    private CarSaleViewModel car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private double Discount;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SalesViewModel() {
    }

    public SalesViewModel(CarSaleViewModel car, String customerName, double discount, BigDecimal price) {
        this.car = car;
        this.customerName = customerName;
        Discount = discount;
        this.price = price;
        this.priceWithDiscount=price.multiply(new BigDecimal(1-discount)).setScale(2,BigDecimal.ROUND_DOWN);
    }

    public CarSaleViewModel getCar() {
        return car;
    }

    public void setCar(CarSaleViewModel car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
