import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Project> resultlist = em
                .createQuery("SELECT p from Project p order by p.startDate desc ")
                .setMaxResults(10)
                .getResultList();

        resultlist.stream().sorted((x,y)->x.getName().compareTo(y.getName())).forEach(x->{
            System.out.printf("Project name: %s%n",x.getName());
            System.out.printf("        Project Description: %s%n",x.getDescription());
            System.out.printf("        Project Start Date:%s%n",x.getStartDate());
            System.out.printf("        Project End Date:%s%n",x.getEndDate());
        });
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
