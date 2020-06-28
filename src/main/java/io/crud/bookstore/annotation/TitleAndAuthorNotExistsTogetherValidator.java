package io.crud.bookstore.annotation;

import io.crud.bookstore.repository.BookRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.util.HashMap;

/**
 * Custom Annotation implementation to validate a record with title/author pair exits in DB.
 */
public class TitleAndAuthorNotExistsTogetherValidator implements ConstraintValidator<TitleAndAuthorNotExistsTogether, Object> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void initialize(TitleAndAuthorNotExistsTogether constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        HashMap<String, String> keyValuePairOfObject = new HashMap<>();
        PropertyDescriptor[] objDescriptors = PropertyUtils.getPropertyDescriptors(o);
        for (PropertyDescriptor objDescriptor : objDescriptors) {
            try {
                if(objDescriptor.getName().equals("class"))
                    continue;
                else {
                    String propertyName = objDescriptor.getName();
                    Object propValue = PropertyUtils.getProperty(o, propertyName);
                    keyValuePairOfObject.put(propertyName, (String)propValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        String title = keyValuePairOfObject.get("title");
        String author = keyValuePairOfObject.get("author");
        return !bookRepository.existsByTitleAndAuthor(title, author);
    }
}
