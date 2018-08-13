package application.services.Author;

import application.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){

        this.authorRepository=authorRepository;
    }


    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public void getAllAuthorsWithBooksBefore(int year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String dateString="01/01/"+year;
        Date date = formatter.parse(dateString);
        Set<Author> authors = new HashSet<>(this.authorRepository.findAllByBooksReleaseDateBefore(date));

        authors.forEach(x->{
            System.out.printf("%s %s%n",x.getFirstName(),x.getLastName());
        });

    }

    @Override
    public void getAllAuthorsByBooks() {
        List<String> list = this.authorRepository.getAllAuthorsOrOrderByBookCountDesc();
        list.forEach(x->{
            System.out.printf("%s%n",x);
        });
    }
}
