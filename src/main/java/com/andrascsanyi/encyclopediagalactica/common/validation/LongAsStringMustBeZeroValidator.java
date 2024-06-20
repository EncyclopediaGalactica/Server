package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class LongAsStringMustBeZeroValidator
    implements ConstraintValidator<LongAsStringMustBeZero, String> {
    
    private final Logger log = LoggerFactory.getLogger(LongAsStringMustBeZeroValidator.class);
    
    @Override
    public void initialize(LongAsStringMustBeZero constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty() || value.isBlank() || value.trim().isEmpty()) {
            return true;
        }
        
        try {
            long longValue = Long.parseLong(value);
            
            boolean isValid = longValue == 0;
            
            if (!isValid) {
                StringBuilder builder = new StringBuilder();
                builder
                    .append("{")
                    .append(getPackageAndAnnotationName(LongAsStringMustBeZero.class))
                    .append(".")
                    .append("message=")
                    .append("When the provided string value: ")
                    .append(value)
                    .append(" is converted to Long it must be equal to 0")
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
}
