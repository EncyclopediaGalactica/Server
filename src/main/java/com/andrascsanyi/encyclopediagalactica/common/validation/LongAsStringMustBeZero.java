package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates if the provided input {@link String} is equal to 0 when it is parsed as Long.
 *
 * <p>This validation annotation is used to check values coming via GraphQL where the ID is {@link
 * String}.
 *
 * <p>The validation judges the negative value, the null, empty or blank string values to be valid.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongAsStringMustBeZeroValidator.class)
@Documented
public @interface LongAsStringMustBeZero {
    String message() default
            "{javax.validation.constraints.LongAsStringMustBeZero.message="
                    + "The Long -{value}- provided as string must be zero!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
