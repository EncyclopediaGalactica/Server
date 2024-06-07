package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimmedNotEmptyValidator
    implements ConstraintValidator<TrimmedNotEmtpy, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "{javax.validation.constraints.NotEmpty.message=" +
                    "When the provided string -{value}- is trimmed it must not be empty}"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(TrimmedNotEmtpy constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
