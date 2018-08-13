import ingredient.*;
import label.BasicLabel;
import shampoo.BasicShampoo;
import shampooMark.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        Set<BasicIngredient> ingredients = new HashSet<>();
        //ingredients.add(mint);
        //ingredients.add(am);
        //ingredients.add(nettle);
        //shampoo.setIngredients(ingredients);
        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(am);
        shampoo.getIngredients().add(nettle);
        em.persist(shampoo);


        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
