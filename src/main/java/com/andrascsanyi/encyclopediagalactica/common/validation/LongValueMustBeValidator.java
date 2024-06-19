package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongValueMustBeValidator implements ConstraintValidator<LongValueMustBe, Long> {
    private Long mustNotBe;
    
    @Override
    public void initialize(LongValueMustBe constraintAnnotation) {
        mustNotBe = constraintAnnotation.mustBe();
    }
    
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!value.equals(mustNotBe)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "The provided value -{value}- cannot be anything than zero.")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
