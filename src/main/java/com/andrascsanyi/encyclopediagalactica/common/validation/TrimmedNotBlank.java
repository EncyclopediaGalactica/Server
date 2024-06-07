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
@Constraint(validatedBy = TrimmedNotBlankValidator.class)
@Documented
public @interface TrimmedNotBlank {
    
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".TrimmedNotBlank.message=" +
        "When the provided string -{value}- is trimmed it must not be blank.}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
