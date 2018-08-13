package application.repositories;

import application.models.Book;
import application.models.enums.AgeRestriction;
import application.models.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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


    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int count);
    List<Book> getAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal loBound, BigDecimal highBound);
    List<Book> getAllByReleaseDateBeforeOrReleaseDateAfter(Date before, Date after);
    List<Book> getAllByReleaseDateBefore(Date date);
    List<Book> getAllByTitleLike(String param);
    List<Book> getAllByAuthor_LastNameStartsWith(String param);
    @Query("SELECT count(b.id) from Book b where LENGTH(b.title) > :size")
    Integer countAllByTitleLength(@Param(value = "size") int count);
    @Query("SELECT concat(b.author.firstName,' ',b.author.lastName,' - ',sum (b.copies))" +
            " from Book b group by b.author order by sum (b.copies) desc ")
    List<String> getCountByAuthor();
    List<Book> findAllByTitle(String title);

    @Modifying
    @Query("UPDATE Book b set b.copies=b.copies+:increaseCopies where b.releaseDate > :inputDate")
    Integer updateIncreaseCopiesWithDateAfter(@Param(value = "increaseCopies") int increaseCopies, @Param(value = "inputDate") Date date);
    @Modifying
    @Query("DELETE from Book as b where b.copies<:countCopies")
    Integer deleteAllByCopiesLessThan(@Param(value = "countCopies") int count);
    @Query(value = "CALL returnNumberOfBooks(?1,?2)", nativeQuery = true)
    Integer callProcedure(String firstName, String lastName);

}
