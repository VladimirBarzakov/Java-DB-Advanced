package ingredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends  BasicIngredient
implements ChemicalIngredient{

    @Column(name="chemical_formula")
    String chemicalFormula;

    @Override
    public String getChemicalFormula() {
        return chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    protected BasicChemicalIngredient(){}

    public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }
}
