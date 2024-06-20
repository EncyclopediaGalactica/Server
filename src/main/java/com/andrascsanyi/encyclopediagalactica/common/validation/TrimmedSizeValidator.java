package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class TrimmedSizeValidator implements ConstraintValidator<TrimmedSize, String> {
    
    private int min;
    private int max;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedSize.class))
                .append(".")
                .append("message=")
                .append("The provided string is null.")
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
            return false;
        }
        
        if (value.trim().length() < min || value.trim().length() > max) {
            StringBuilder builder = new StringBuilder();
            builder.append("{")
                .append(getPackageAndAnnotationName(TrimmedSize.class))
                .append(".")
                .append("message=")
                .append("When the provided string (")
                .append(value)
                .append(") is trimmed its size must be greater or equal to ")
                .append(min)
                .append(" and shorter than ")
                .append(max)
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString()).addConstraintViolation();
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
