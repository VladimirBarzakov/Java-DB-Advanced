package application.repositories;

import application.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    List<Author> findAllByBooksReleaseDateBefore(Date date);
    @Query(value = "SELECT concat_ws(\" \",a.first_name,a.last_name, count(b.id)) from authors as a\n" +
            "JOIN books as b on a.id=b.author_id\n" +
            "GROUP BY a.id\n" +
            "order by count(b.id) DESC", nativeQuery = true)
    List<String> getAllAuthorsOrOrderByBookCountDesc();
}
