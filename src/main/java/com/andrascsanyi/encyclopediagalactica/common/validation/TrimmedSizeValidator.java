package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimmedSizeValidator implements ConstraintValidator<TrimmedSize, String> {
    
    private int min;
    private int max;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedSizeValidator.message=" +
                    "The provided value(-{value}-} is null."
            ).addConstraintViolation();
            return false;
        }
        
        if (value.trim().length() < min || value.trim().length() > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedSizeValidator.message=" +
                    "The provided value(-{value}-} trimmed length is either " +
                    "longer than {max} or shorter than {min}."
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(TrimmedSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }
}
