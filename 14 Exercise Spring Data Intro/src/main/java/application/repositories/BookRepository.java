package application.repositories;

import application.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    List<Book> findAllByReleaseDateAfter(Date year);
    @Query(value = "SELECT b.title FROM books as b\n" +
            "JOIN authors as a on b.author_id=a.id\n" +
            "WHERE a.first_name=\"George\" and a.last_name=\"Powell\"\n" +
            "ORDER BY b.release_date DESC, b.title;",nativeQuery = true)
    List<String> findAllBooksByGeorgePowell();

}
