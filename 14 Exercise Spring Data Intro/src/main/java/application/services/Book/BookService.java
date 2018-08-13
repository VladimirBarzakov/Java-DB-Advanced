package application.services.Book;

import application.models.Book;

import java.text.ParseException;

public interface BookService {
    void save(Book book);

    void getAllBooksAfterYear(int year) throws ParseException;

    void getAllBooksByGeorgePowell();
}
