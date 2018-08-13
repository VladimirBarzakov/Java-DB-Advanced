import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        String inputName = scanner.nextLine();

        Town town = (Town)em.createQuery("select t from  Town t where t.name=?")
                .setParameter(0,inputName)
                .getResultList().get(0);
        List<Address> addresses = em.createQuery("SELECT a from Address a where a.town.name=?").setParameter(0,inputName)
                .getResultList();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee e where e.address.town.name=?")
                .setParameter(0,inputName)
                .getResultList();

        for (Employee employee : employees) {
            em.detach(employee);
            employee.setAddress(null);
            em.merge(employee);
        }

        int adressCount = addresses.size();
        for (Address address : addresses) {
            em.remove(address);
        }
        em.remove(town);
        System.out.printf("%d address in %s deleted%n",adressCount,inputName);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
