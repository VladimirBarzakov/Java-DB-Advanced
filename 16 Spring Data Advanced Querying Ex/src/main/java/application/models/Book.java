package application.models;

import application.models.enums.AgeRestriction;
import application.models.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50,nullable = false)
    private String title;

    @Column(length = 1000,nullable = true)
    private String description;

    private EditionType editionType;

    private int copies;

    private BigDecimal price;

    @Column(name="release_date", nullable = true)
    private Date releaseDate;

    private AgeRestriction ageRestriction;

    @ManyToOne(targetEntity = Author.class)
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_category",joinColumns = {@JoinColumn(name="book_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id",referencedColumnName = "id")})
    private Set<Category> category;

    public Book(){this.category=new HashSet<Category>();}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }


}
