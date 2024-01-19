package it.itsrizzoli.webbookdemo.Model;

import it.itsrizzoli.webbookdemo.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 30)
    String title;

    @NotNull
    @Size(min = 2, max = 50)
    String author;

    @Min(1900)
    @Max(2024)
    Integer publicationYear;

    @Min(5)
    @Max(100)
    Integer price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<BookUser> bookUser = new HashSet<>();

    public Book(String title, String author, Integer publicationYear, Integer price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    public Book() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Set<BookUser> getBookUser() {
        return bookUser;
    }

    public void setBookUser(Set<BookUser> bookUser) {
        this.bookUser = bookUser;
    }
}
