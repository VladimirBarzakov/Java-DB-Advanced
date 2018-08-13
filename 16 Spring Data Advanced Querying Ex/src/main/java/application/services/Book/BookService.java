package application.services.Book;

import application.dto.ReducedBook;
import application.models.Book;
import application.models.enums.EditionType;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {
    void save(Book book);

    void getAllBooksAfterYear(int year) throws ParseException;

    void getAllBooksByGeorgePowell();

    void getAllBooksByAgeRestriction(String ageRestriction);

    void getAllBooksByEditionTypeAndCopies();

    void getAllBooksNotInPriceRange();

    void notReleasedBooks(int year) throws ParseException;

    void getReleasedBooksBefore(String string) throws ParseException;

    void getAllBooksWithTitleLike(String param);

    void getAllBooksWithAuthorLastNameStartWith(String param);

    void getAllBooksWithTitleLengthMoreThan(int count);

    void getAllCopiesByAuthors();

    List<ReducedBook> getReducedBooksByTitle(String title);

    int deleteBooksWithCopiesLowerThan(Integer count);

    int updateBooksCopiesAfter(Integer copies,String dateStr) throws ParseException;

    int callProcedure(String firstName, String lastName);
}
