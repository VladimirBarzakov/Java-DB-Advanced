import entities.Address;
import javafx.util.Pair;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Address> resultSet=em
                .createQuery("SELECT a from Address a order by a.employees.size desc,a.town.id asc ")
                .setMaxResults(10)
                .getResultList();

        resultSet.forEach(x-> System.out.printf("%s, %s - %d employees"+System.lineSeparator(),x.getText(),x.getTown().getName(),x.getEmployees().size()));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
