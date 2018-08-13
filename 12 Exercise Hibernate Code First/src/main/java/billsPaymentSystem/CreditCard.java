package billsPaymentSystem;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
@DiscriminatorValue(value = "CREDIT_CARD")
public class CreditCard  extends BillingDetails{

    @Basic
    @Column(name="card_type")
    private String cardType;

    @Basic
    @Column(name="expiration_month")
    private int expirationMonth;

    @Basic
    @Column(name="expiration_year")
    private int expirationYear;


    public CreditCard(){}

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
