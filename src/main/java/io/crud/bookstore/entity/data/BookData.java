package io.crud.bookstore.entity.data;

import io.crud.bookstore.annotation.TitleAndAuthorNotExistsTogether;
import io.crud.bookstore.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * A Data transfer object to get the information regarding book entity from client
 * A book entity has title & author
 *  - Same title/author pair should not exist in DB
 *      - @TitleAndAuthorNotExistsTogether check does that validation
 *  - Title/Author fields must be filled with string.
 *      - e.g. @NotEmpty check does that validation
 *  - Title/Author fields must not be sent as NULL values
 *      - e.g. @NotNull check does that validation
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@TitleAndAuthorNotExistsTogether(message = Constants.TITLE_AND_AUTHOR_EXISTS)
public class BookData {
    @NotNull(message = Constants.TITLE_REQUIRED)
    @NotEmpty(message = Constants.TITLE_NOT_EMPTY)
    private String title;


    @NotNull(message = Constants.AUTHOR_REQUIRED)
    @NotEmpty(message = Constants.AUTHOR_NOT_EMPTY)
    private String author;
}
