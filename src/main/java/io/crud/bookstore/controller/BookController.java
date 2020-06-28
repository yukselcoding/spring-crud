package io.crud.bookstore.controller;

import io.crud.bookstore.constants.Constants;
import io.crud.bookstore.entity.Book;
import io.crud.bookstore.entity.data.BookData;
import io.crud.bookstore.entity.data.ResponseError;
import io.crud.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


/**
 * Rest Controller for bookstore
 * Implements HTTP/GET & HTTP/PUT methods
 */
@RestController
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Returns all books in DB
     * @return
     */
    @GetMapping("/api/books/")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }


    /**
     * Returns one book with specified id, if not exists throws 404-NOT_FOUND HTTP code message
     * @param id
     * @return
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        return bookFromDb.map(book -> {
            return new ResponseEntity<Object>(book, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponseError(String.format(Constants.BOOK_NOT_EXISTS, id)),
                HttpStatus.NOT_FOUND));
    }

    /**
     * Creates new book if not any record exists with same author/title pair
     * @param id
     * @param bookToPut
     * @return
     */
    @PostMapping("/api/books/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody BookData bookToPut) {
        Optional<Book> bookSaved = Optional.ofNullable(bookRepository.save(new Book(bookToPut)));
        return bookSaved.map(book -> {
            return new ResponseEntity<Object>(book, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponseError(String.format(Constants.SERVER_ERROR, id)),
                HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
