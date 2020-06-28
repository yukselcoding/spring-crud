package io.crud.bookstore;


import io.crud.bookstore.constants.Constants;
import io.crud.bookstore.entity.Book;
import io.crud.bookstore.entity.data.BookData;
import io.crud.bookstore.entity.data.ResponseError;
import io.crud.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private TestRestTemplate restTemplate;


    @LocalServerPort
    int randomServerPort;

    @Test
    public void test_getOne_Success() {
        Book expectedGetOneResponseBook = new Book((long) 1, "Lord Of The Rings", "J.R.R Tolkien");
        given(bookRepository.findById((long) 1)).willReturn(Optional.of(expectedGetOneResponseBook));
        ResponseEntity<Book> actualGetOneResponse = restTemplate.getForEntity("/api/books/1", Book.class);
        assertThat(actualGetOneResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualGetOneResponse.equals(new ResponseEntity<>(expectedGetOneResponseBook, HttpStatus.OK)));
    }

    @Test
    public void test_getOne_Failure() {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(new ResponseError(String.format(Constants.BOOK_NOT_EXISTS, 1)), HttpStatus.NOT_FOUND);
        ResponseEntity<ResponseError> actualResponse = restTemplate.getForEntity("/api/books/1", ResponseError.class);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(actualResponse.equals(expectedResponse));
    }

    @Test
    public void test_getAll_NoBooks() {
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(new ArrayList<Book>(), HttpStatus.OK);
        ResponseEntity<List> actualResponse = restTemplate.getForEntity("/api/books/", List.class);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.equals(expectedResponse));
    }

    @Test
    public void test_getAll_WithBooks() {
        List<Book> expectedBooks = Arrays.asList(new Book((long) 1, "Lord Of The Rings", "J.R.R Tolkien"),
                new Book((long) 2, "Harry Potter", "J.K. Rowling"));
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedBooks, HttpStatus.OK);
        given(bookRepository.findAll()).willReturn(expectedBooks);
        ResponseEntity<List> actualResponse = restTemplate.getForEntity("/api/books/", List.class);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.equals(expectedResponse));
    }

//    @Test
//    public void test_put_Success() throws URISyntaxException {
//        final String baseUrl = "http://localhost:"+randomServerPort+"/api/books/1";
//        URI uri = new URI(baseUrl);
//        Book expectedResponseBook = new Book((long) 1, "Lord Of The Rings", "J.R.R Tolkien");
//        ResponseEntity<Book> expectedResponse = new ResponseEntity<>(expectedResponseBook, HttpStatus.OK);
//        BookData sentRequestData = new BookData("Lord Of The Rings", "J.R.R Tolkien");
//        ResponseEntity<Book> actualResponse = restTemplate.postForEntity(uri, sentRequestData, Book.class);
//        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actualResponse.getBody().equals(expectedResponse.getBody()));
//    }















}
