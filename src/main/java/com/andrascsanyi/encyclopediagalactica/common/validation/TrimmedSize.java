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
@Constraint(validatedBy = TrimmedSizeValidator.class)
@Documented
public @interface TrimmedSize {
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".TrimmedSize" +
        ".message=When the provided string is trimmed it must be longer than and shorter than defined.}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    int min() default 0;
    
    int max() default Integer.MAX_VALUE;
}
