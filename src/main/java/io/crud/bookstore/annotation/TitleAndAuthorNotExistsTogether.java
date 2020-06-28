package io.crud.bookstore.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom Annotation to validate a record with title/author pair exits in DB.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleAndAuthorNotExistsTogetherValidator.class)
public @interface TitleAndAuthorNotExistsTogether {
    String message() default "";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
