package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates if the provided {@link String} value is empty after it is trimmed.
 * <p>
 * The validator judges null to be invalid value.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrimmedNotEmptyValidator.class)
@Documented
public @interface TrimmedNotEmpty {
    
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".TrimmedNotEmpty" +
        ".message=When the provided string is trimmed it must not be empty}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
