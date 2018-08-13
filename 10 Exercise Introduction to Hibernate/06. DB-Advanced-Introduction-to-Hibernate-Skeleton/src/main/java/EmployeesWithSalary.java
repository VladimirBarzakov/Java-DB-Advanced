import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesWithSalary {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e.firstName from Employee e where e.salary>50000");

        List<String> resultList = query.getResultList();

        for (String name:resultList) {
            System.out.println(name);
        }
        em.getTransaction().commit();

        em.close();
        factory.close();
    }
}
