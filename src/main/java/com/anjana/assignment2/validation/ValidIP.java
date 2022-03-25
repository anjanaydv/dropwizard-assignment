package com.anjana.assignment2.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Anjana Yadav
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 * <p>
 * Custom annotation for ip validator
 */
@Documented
@Constraint(validatedBy = IpValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIP {
    String message() default "Invalid IP Address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}