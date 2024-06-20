package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates if the provided input {@link String} is equal or greater than 0 when it is parsed as Long.
 *
 * <p>This validation annotation is used to check values coming via GraphQL where the ID is {@link
 * String}
 *
 * <p>The validation judges the null, empty or blank string values to be valid. The validation
 * judges the negative value to be invalid.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongAsStringMustBeGreaterOrEqualToOneValidator.class)
@Documented
public @interface LongAsStringMustBeGreaterOrEqualToOne {
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".LongAsStringMustBeGreaterOrEqualToOne" +
        ".message=The provided Long value as String must be equal to zero}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
