public class Book {
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private String ISBN_number;
    private Double price;

    public Book(){}


    public Book(String title, String author, String publisher, String releaseDate, String ISBN_number, Double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.ISBN_number = ISBN_number;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getISBN_number() {
        return ISBN_number;
    }

    public Double getPrice() {
        return price;
    }
}
