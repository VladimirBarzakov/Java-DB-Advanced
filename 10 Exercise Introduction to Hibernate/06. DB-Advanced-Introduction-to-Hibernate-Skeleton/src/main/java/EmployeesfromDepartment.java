import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesfromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Employee e JOIN e.department d where d.name='Research and Development' order by e.salary,e.id");

        List<Employee> resultList = query.getResultList();

        resultList.stream().forEach(x-> System.out.printf("%s %s from Research and Development - $%.2f"+System.lineSeparator(),x.getFirstName(),x.getLastName(),x.getSalary()));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
