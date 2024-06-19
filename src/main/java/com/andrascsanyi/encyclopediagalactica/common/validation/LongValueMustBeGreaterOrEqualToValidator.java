package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongValueMustBeGreaterOrEqualToValidator
    implements ConstraintValidator<LongValueMustBeGreaterOrEqualTo, Long> {
    
    private Long mustBeGreaterToEqualToValue;
    
    @Override
    public void initialize(LongValueMustBeGreaterOrEqualTo constraintAnnotation) {
        this.mustBeGreaterToEqualToValue = constraintAnnotation.mustBeGreaterOrEqualTo();
    }
    
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value < mustBeGreaterToEqualToValue) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "The provided value -{value}- must be equal or greater to: " + mustBeGreaterToEqualToValue)
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
