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

        this.bookService.getAllBooksByGeorgePowell();

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
            String catetegory = line;
            Category category = new Category();
            category.setName(catetegory);
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
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

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
