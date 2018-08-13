import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class AddingNewAddress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em =factory.createEntityManager();

        em.getTransaction().begin();
        Town town = (Town)em.createQuery("SELECT t from Town t where t.name='Sofia'").getResultList().get(0);
        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        em.persist(address);
        Query query = em.createQuery("select e from Employee e where e.lastName=:name");
        query.setParameter("name",input);
        Employee employee = (Employee) query.getResultList().get(0);
        em.detach(employee);
        employee.setAddress(address);
        em.merge(employee);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
