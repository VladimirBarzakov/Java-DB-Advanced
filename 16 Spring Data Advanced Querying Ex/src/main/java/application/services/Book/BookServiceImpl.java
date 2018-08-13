package application.services.Book;

import application.dto.ReducedBook;
import application.models.Book;
import application.models.enums.AgeRestriction;
import application.models.enums.EditionType;
import application.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        this.bookRepository.findAllBooksByGeorgePowell().forEach(x-> System.out.printf("%s%n",x));
    }


    @Override
    public void getAllBooksByAgeRestriction(String ageRestrictionStr) {
        AgeRestriction age = AgeRestriction.valueOf(ageRestrictionStr.toUpperCase());
        this.bookRepository.findAllByAgeRestriction(age).forEach(x-> System.out.printf("%s%n",x.getTitle()));
    }

    @Override
    public void getAllBooksByEditionTypeAndCopies() {
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000).forEach(x->{
            System.out.printf("%s%n",x.getTitle());
        });
    }

    @Override
    public void getAllBooksNotInPriceRange() {
        this.bookRepository.getAllByPriceIsLessThanOrPriceIsGreaterThan(new BigDecimal("5"), new BigDecimal("40")).forEach(x->{
            System.out.printf("%s - $%.2f %n",x.getTitle(),x.getPrice());
        });
    }


    @Override
    public void notReleasedBooks(int year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String dateString="31/12/"+year;
        Date after = formatter.parse(dateString);
        dateString="01/01/"+year;
        Date before = formatter.parse(dateString);

        this.bookRepository.getAllByReleaseDateBeforeOrReleaseDateAfter(before,after).forEach(x->{
            System.out.printf("%s%n",x.getTitle());
        });
    }

    @Override
    public void getReleasedBooksBefore(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
        Date date = formatter.parse(stringDate);
        this.bookRepository.getAllByReleaseDateBefore(date).forEach(x->{
            System.out.printf("%s%n",x.getTitle());
        });
    }

    @Override
    public void getAllBooksWithTitleLike(String param) {
        String likeParam = "%"+param+"%";
        this.bookRepository.getAllByTitleLike(likeParam).forEach(x->{
            System.out.printf("%s%n",x.getTitle());
        });
    }

    @Override
    public void getAllBooksWithAuthorLastNameStartWith(String param) {
        this.bookRepository.getAllByAuthor_LastNameStartsWith(param).forEach(x->{
            System.out.printf("%s (%s %s)%n",x.getTitle(),x.getAuthor().getFirstName(),x.getAuthor().getLastName());
        });
    }

    @Override
    public void getAllBooksWithTitleLengthMoreThan(int count) {
        System.out.println("Count of books is "+this.bookRepository.countAllByTitleLength(count));
    }

    @Override
    public void getAllCopiesByAuthors() {
        this.bookRepository.getCountByAuthor().forEach(x->{
            System.out.printf("%s%n",x);
        });
    }

    @Override
    public List<ReducedBook> getReducedBooksByTitle(String title) {
        return this.bookRepository.findAllByTitle(title).stream().
                map(x->new ReducedBook(x.getTitle(),x.getEditionType(),x.getAgeRestriction(),x.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public int deleteBooksWithCopiesLowerThan(Integer count) {
        return this.bookRepository.deleteAllByCopiesLessThan(count);
    }

    @Override
    public int updateBooksCopiesAfter(Integer copies, String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date date = formatter.parse(dateStr);
        return this.bookRepository.updateIncreaseCopiesWithDateAfter(copies, date);
    }

    @Override
    public int callProcedure(String firstName, String lastName) {
        return this.bookRepository.callProcedure(firstName,lastName);
    }
}
