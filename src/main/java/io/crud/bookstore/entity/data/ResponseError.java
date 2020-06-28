package io.crud.bookstore.entity.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Data transfer object to return custom error messages to client
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ResponseError {
    private String error;
}
