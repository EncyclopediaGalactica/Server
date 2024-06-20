package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class TrimmedNotEmptyValidator
    implements ConstraintValidator<TrimmedNotEmpty, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (value == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedNotEmpty.class))
                .append(".")
                .append("message=")
                .append("The input is null. The validation cannot be executed.")
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
            return false;
        }
        
        if (value.trim().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedNotEmpty.class))
                .append(".")
                .append("message=")
                .append("When the provided string (")
                .append(value)
                .append(") is trimmed it must not be empty.")
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(TrimmedNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
