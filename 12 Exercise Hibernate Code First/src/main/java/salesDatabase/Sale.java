package salesDatabase;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Table(name = "sale")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(targetEntity = Product.class)
    private Product productId;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customerId;

    @ManyToOne(targetEntity = StoreLocation.class)
    private StoreLocation storeLocationId;

    @Column(name="date")
    private Date date;

    public Sale() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public StoreLocation getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(StoreLocation storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
