package ru.flamexander.spring.data.jdbc.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("BOOKS")
public class Book {
    @Id
    private Long id;
    private String title;
    private Long authorId;
    private Genre genre;

    private List<Review> reviews;
    private double averageRate;

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public double getAverageRate() {
        return averageRate;
    }

    @MappedCollection(idColumn = "BOOK_ID")
    private BookDetails bookDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @PersistenceCreator
    public Book(Long id, String title, Long authorId, BookDetails bookDetails) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.bookDetails = bookDetails;
    }
}
