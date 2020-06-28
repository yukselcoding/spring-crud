package io.crud.bookstore.entity;

import io.crud.bookstore.entity.data.BookData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Database Entity representing Book model in our project
 * - ID : auto generated - Primary Key in our book table
 * - Title: title of the book
 * - Author: author of the book
 * - Title & Author is unique together since we have constraints as such
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "author"})
})
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field 'title' cannot be empty.")
    @NotNull(message = "Field 'title' is required.")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Field 'author' cannot be empty.")
    @NotNull(message = "Field 'author' is required.")
    @Column(name = "author")
    private String author;

    public Book() {
    }

    public Book(BookData bookToPut) {
        this.title = bookToPut.getTitle();
        this.author = bookToPut.getAuthor();
    }

    public Book(Long id, String title, String author) {
        this.id = id;
        this.author = author;
        this.title = title;
    }
}
