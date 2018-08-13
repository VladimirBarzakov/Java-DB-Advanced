package shampooMark;

import label.BasicLabel;
import shampoo.BasicShampoo;
import shampoo.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PN")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panter";

    private static final BigDecimal PRICE = new BigDecimal("8.50");

    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther(BasicLabel label){
        super(BRAND,PRICE,SIZE,label);
    }
}
