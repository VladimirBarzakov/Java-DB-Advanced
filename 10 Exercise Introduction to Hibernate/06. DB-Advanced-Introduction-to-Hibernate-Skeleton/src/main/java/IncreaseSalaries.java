import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> empList =em.createQuery("select e from Employee e " +
                "where e.department.name in ('Engineering','Tool Design','Marketing','Information Services')").getResultList();

        for (Employee employee : empList) {
            em.detach(employee);
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12d) ));
            System.out.printf("%s %s ($%.2f)%n",employee.getFirstName(),employee.getLastName(),employee.getSalary().doubleValue());
            em.merge(employee);
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
