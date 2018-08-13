package application.services.Author;

import application.models.Author;

import java.text.ParseException;
import java.util.List;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    List<Author> getAllAuthors();

    void getAllAuthorsWithBooksBefore(int year) throws ParseException;

    void getAllAuthorsByBooks();
}
