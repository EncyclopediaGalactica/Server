package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class LongAsStringMustBeGreaterOrEqualToOneValidator
    implements ConstraintValidator<LongAsStringMustBeGreaterOrEqualToOne, String> {
    
    private final Logger log = LoggerFactory.getLogger(LongAsStringMustBeGreaterOrEqualToOne.class);
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty() || value.isBlank() || value.trim().isEmpty()) {
            return true;
        }
        
        try {
            long longValue = Long.parseLong(value);
            
            boolean isValid = longValue >= 1;
            
            if (!isValid) {
                StringBuilder builder = new StringBuilder();
                builder
                    .append("{")
                    .append(getPackageAndAnnotationName(LongAsStringMustBeGreaterOrEqualToOne.class))
                    .append(".")
                    .append("message=")
                    .append("When the provided input string (").append(value).append(") is converted to Long ")
                    .append("it must be greater or equal to 1")
                    .append("}");
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(builder.toString())
                    .addConstraintViolation();
            }
            
            return isValid;
            
        } catch (NumberFormatException e) {
            log.warn("Could not parse long value: {}", value);
        }
        return false;
    }
    
    @Override
    public void initialize(LongAsStringMustBeGreaterOrEqualToOne constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
