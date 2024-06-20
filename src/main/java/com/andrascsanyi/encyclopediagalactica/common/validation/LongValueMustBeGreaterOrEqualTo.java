package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongValueMustBeGreaterOrEqualToValidator.class)
@Documented
public @interface LongValueMustBeGreaterOrEqualTo {
    long mustBeGreaterOrEqualTo() default Long.MIN_VALUE;
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".LongValueMustBeGreaterOrEqualT" +
        ".message=The provided value must be greater than or equal to the defined one}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
