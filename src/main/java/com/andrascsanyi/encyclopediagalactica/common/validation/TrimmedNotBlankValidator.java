package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class TrimmedNotBlankValidator
    implements ConstraintValidator<TrimmedNotBlank, String> {
    
    private final static Logger log = LoggerFactory.getLogger(TrimmedNotBlankValidator.class);
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedNotBlank.class))
                .append(".")
                .append("message=")
                .append("The provided string is null. Validation cannot be executed.")
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
            return false;
            
        }
        
        if (value.trim().isBlank()) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedNotBlank.class))
                .append(".")
                .append("message=")
                .append("When the provided string (")
                .append(value)
                .append(") is trimmed, it cannot be blank.")
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(TrimmedNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
