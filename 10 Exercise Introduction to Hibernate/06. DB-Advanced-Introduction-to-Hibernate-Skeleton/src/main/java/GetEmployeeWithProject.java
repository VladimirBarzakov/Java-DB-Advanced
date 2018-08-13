import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputEmpId = Integer.parseInt(scanner.nextLine());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = (Employee) em.createQuery("SELECT e from Employee e where e.id=?").setParameter(0,inputEmpId).getResultList().get(0);
        System.out.printf("%s %s - %s %s%n",employee.getFirstName(),employee.getLastName(),employee.getDepartment().getName(),employee.getJobTitle());
        for (Project project : employee.getProjects().stream().sorted((x,y)->x.getName().compareTo(y.getName())).collect(Collectors.toList()) ) {
            System.out.printf("      %s%n",project.getName());
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
