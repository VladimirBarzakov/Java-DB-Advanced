package billsPaymentSystem;

import javax.persistence.*;

//@Entity
@DiscriminatorValue(value = "BANK_ACCAUNT")
public class BankAccaunt extends BillingDetails {


    @Column(name="name")
    private String name;

    @Basic
    @Column(name="SWIFT")
    private String SWIFT;

    public BankAccaunt(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSWIFT() {
        return SWIFT;
    }

    public void setSWIFT(String SWIFT) {
        this.SWIFT = SWIFT;
    }
}
