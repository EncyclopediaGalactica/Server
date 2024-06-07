package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrimmedNotBlankValidator
    implements ConstraintValidator<TrimmedNotBlank, String> {
    
    private final static Logger log = LoggerFactory.getLogger(TrimmedNotBlankValidator.class);
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "{com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotBlank=" +
                    "The provided value (-{value}-) is null.}"
            ).addConstraintViolation();
            return false;
            
        }
        
        if (value.trim().isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "{javax.validation.constraints.NotBlank.message=" +
                    "When the provided string -{value}- is trimmed it must not be blank.}"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(TrimmedNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
