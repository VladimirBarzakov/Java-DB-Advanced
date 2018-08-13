package application.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author", targetEntity = Book.class, cascade = CascadeType.ALL)
    private Set<Book> books;

    @Transient
    private int bookCount;

    public Author(){this.books=new HashSet<Book>();}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int getBookCount() {
        return this.books.size();
    }

}
