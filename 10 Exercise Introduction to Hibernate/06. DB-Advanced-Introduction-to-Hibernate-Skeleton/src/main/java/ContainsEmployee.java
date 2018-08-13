
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputParam = scanner.nextLine().replaceAll(" ","");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT e from  Employee e where concat(e.firstName,e.lastName) =:name ");
        query.setParameter("name",inputParam);

        List employee =  query.getResultList();

        if (employee.size()!=0){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
