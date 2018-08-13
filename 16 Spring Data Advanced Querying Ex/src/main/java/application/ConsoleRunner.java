package application;

import application.models.Author;
import application.models.Book;
import application.models.Category;
import application.models.enums.AgeRestriction;
import application.models.enums.EditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import application.services.Author.AuthorServiceImpl;
import application.services.Book.BookServiceImpl;
import application.services.Category.CategoryServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private AuthorServiceImpl authorService;
    private BookServiceImpl bookService;
    private CategoryServiceImpl categoryService;

    @Autowired
    public ConsoleRunner(AuthorServiceImpl authorService, BookServiceImpl bookService, CategoryServiceImpl categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {

        //inputData();

        //getAllBooksAfterYear(2014);
        //getAllAuthorsWithBookBefore(1988);
        //getAllAuthorsByBookCount();
        //this.bookService.getAllBooksByGeorgePowell();

        //>>>>   Spring Data Advanced Queering Exercises Part  <<<<<

        Scanner scanner = new Scanner(System.in);

        //System.out.println("Type age restriction parameter:");
        //this.bookService.getAllBooksByAgeRestriction(scanner.nextLine());

        //this.bookService.getAllBooksByEditionTypeAndCopies();

        //this.bookService.getAllBooksNotInPriceRange();

        //System.out.println("Type year:");
        //this.bookService.notReleasedBooks(Integer.parseInt(scanner.nextLine()));

        //System.out.println("Type date:");
        //this.bookService.getReleasedBooksBefore(scanner.nextLine());

        //System.out.println("Type search parameter:");
        //this.bookService.getAllBooksWithTitleLike(scanner.nextLine());

        //System.out.println("Type author starting string:");
        //this.bookService.getAllBooksWithAuthorLastNameStartWith(scanner.nextLine());

        //System.out.println("Type title length:");
        //this.bookService.getAllBooksWithTitleLengthMoreThan(Integer.parseInt(scanner.nextLine()));

        //this.bookService.getAllCopiesByAuthors();

        //System.out.println("Type search title;");
        //this.bookService.getReducedBooksByTitle(scanner.nextLine()).forEach(x->{
        //    System.out.printf("%s %s %s %.2f%n",x.getTitle(),x.getEditionType(),x.getAgeRestriction(),x.getPrice());
        //});

        //System.out.println("Type input parameters of 12 Increase Book Copies exercises");
        //String dateStr =scanner.nextLine();
        //int copies = Integer.parseInt(scanner.nextLine());
        //increaseBookCopies(copies, dateStr);


        //System.out.println("Type number of copies:");
        //System.out.println(this.bookService.deleteBooksWithCopiesLowerThan(Integer.parseInt(scanner.nextLine()))+" books were deleted");


        System.out.println("Type author name:");
        String[] tokens = scanner.nextLine().split("\\s+");
        String firstName = tokens[0];
        String lastName = tokens[1];
        callProcedure(firstName,lastName);
    }

    private void callProcedure(String firstName, String lastName) {
        int result = this.bookService.callProcedure(firstName, lastName);
        if (result==0){
            System.out.printf("%s %s has not written any books yet",firstName,lastName);
        }else{
            System.out.printf("%s %s has written %d book",firstName,lastName,result);
            if (result>1){
                System.out.print("s");
            }
        }
        System.out.println();
    }

    private void increaseBookCopies(int copies, String dateStr) throws ParseException {
        System.out.println(this.bookService.updateBooksCopiesAfter(copies,dateStr)*copies);
    }


    private void getAllAuthorsByBookCount() {
        this.authorService.getAllAuthorsByBooks();
    }

    private void getAllAuthorsWithBookBefore(int year) throws ParseException {
        this.authorService.getAllAuthorsWithBooksBefore(year);
    }

    private void getAllBooksAfterYear(int i) throws ParseException {

        this.bookService.getAllBooksAfterYear(i);
    }

    private void inputData() throws IOException, ParseException {
        //Input data
        Random random = new Random();
        //Input Authors
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("authors.txt").getFile());
        BufferedReader authorsReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line=authorsReader.readLine())!=null){
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorService.saveAuthorIntoDb(author);
        }

        List<Author> authors;
        authors=authorService.getAllAuthors();

        //Input Categories
        file = new File(classLoader.getResource("categories.txt").getFile());
        BufferedReader categoriesReader = new BufferedReader(new FileReader(file));
        while ((line=categoriesReader.readLine())!=null){
            String categoryStr = line;
            Category category = new Category();
            category.setName(categoryStr);
            categoryService.save(category);
        }
        List<Category> categories;
        categories=categoryService.getAllCategories();

        //Input Books
        file = new File(classLoader.getResource("books.txt").getFile());
        BufferedReader booksReader = new BufferedReader(new FileReader(file));

        while((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            String digit = data[0].trim();
            int digitIndex;
            for (digitIndex = 0; digitIndex < digit.length(); digitIndex++) {
                boolean bool = Character.isDigit(digit.charAt(digitIndex));
                if (bool){
                    break;
                }
            }
            int enumIndex = Integer.parseInt(Character.toString(digit.charAt(digitIndex)));
            EditionType editionType = EditionType.values()[enumIndex];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            String title = titleBuilder.toString().trim();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);



            bookService.save(book);

            int categoryIndex=random.nextInt(categories.size());
            Category category = categories.get(categoryIndex);
            Set<Category> categorySet = new HashSet<Category>();
            categorySet.add(category);
            book.setCategory(categorySet);

            bookService.save(book);
        }
        System.out.println("Input data done!");
    }


}
