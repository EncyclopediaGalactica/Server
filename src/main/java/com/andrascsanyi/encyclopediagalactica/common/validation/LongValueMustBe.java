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
@Constraint(validatedBy = LongValueMustBeValidator.class)
@Documented
public @interface LongValueMustBe {
    long mustBe() default 0L;
    String message() default "{com.andrascsanyi.encyclopediagalactica.common.validation" +
        ".LongValueMustBe" +
        ".message=The provided Long value must be equal to the defined one.}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
