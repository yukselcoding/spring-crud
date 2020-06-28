package io.crud.bookstore.constants;


/**
 * General constants used in the project
 */
public class Constants {
    public static final String TITLE_NOT_EMPTY = "Field 'title' cannot be empty.";
    public static final String TITLE_REQUIRED = "Field 'title' is required.";
    public static final String AUTHOR_NOT_EMPTY = "Field 'author' cannot be empty.";
    public static final String AUTHOR_REQUIRED = "Field 'author' is required.";
    public static final String TITLE_AND_AUTHOR_EXISTS = "Same author/title pair already exists.";
    public static final String BOOK_NOT_EXISTS = "Book with id: %s does not exist.";
    public static final String SERVER_ERROR =  "Server Error while saving book with id: %s";
}
