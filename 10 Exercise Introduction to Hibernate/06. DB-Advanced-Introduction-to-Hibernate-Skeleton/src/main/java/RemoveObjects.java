import entities.Town;

import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em =factory.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT t FROM Town as t where length(t.name)>5");
        List<Town> towns = query.getResultList();
        for (Town town:towns) {
            em.detach(town);
            town.setName(town.getName().toLowerCase());
            em.merge(town);
        }

        em.getTransaction().commit();
    }
}
