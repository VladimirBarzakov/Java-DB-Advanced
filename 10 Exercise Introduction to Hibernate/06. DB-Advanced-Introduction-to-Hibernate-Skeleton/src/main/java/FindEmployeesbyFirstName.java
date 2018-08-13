import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesbyFirstName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> result = em.createQuery("SELECT e from Employee e where e.firstName like '"+input+"%'")
                .getResultList();

        for (Employee employee : result) {
            System.out.printf("%s %s - %s %s - ($%.2f)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getJobTitle(),
                    employee.getSalary());
        }
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
