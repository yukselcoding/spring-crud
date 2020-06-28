package io.crud.bookstore.repository;

import io.crud.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This is the repository layer, that interacts with our DB
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    boolean existsByTitleAndAuthor(String title, String author);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
}
