import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> result = new ArrayList<>();
        List<Department> departments = em.createQuery("select d from Department d").getResultList();
        for (Department department : departments) {
            Employee employee =  new Employee();
            try {
                employee= (Employee) em.createQuery("select e from Employee e where e.department.id=:id and e.salary not between 30000 and 70000 order by e.salary desc").
                        setParameter("id",department.getId()).setMaxResults(1)
                        .getSingleResult();
                result.add(employee);
            } catch (NoResultException nre){

            }

        }

        System.out.printf("");
        for (Employee employee : result) {
            System.out.printf("%s - %.2f%n",employee.getDepartment().getName(),employee.getSalary());
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
