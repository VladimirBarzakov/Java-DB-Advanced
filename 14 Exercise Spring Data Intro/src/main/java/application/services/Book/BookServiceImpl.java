package application.services.Book;

import application.models.Book;
import application.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book){
        this.bookRepository.save(book);
    }

    @Override
    public void getAllBooksAfterYear(int year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String dateString="31/12/"+year;
        Date date = formatter.parse(dateString);
        this.bookRepository.findAllByReleaseDateAfter(date)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    @Override
    public void getAllBooksByGeorgePowell() {
        this.bookRepository.findAllBooksByGeorgePowell().forEach(x->{
            System.out.printf("%s%n",x);
        });
    }
}
