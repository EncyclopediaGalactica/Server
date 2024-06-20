package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

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
            StringBuilder builder = new StringBuilder();
            builder
                .append("{")
                .append(getPackageAndAnnotationName(LongValueMustBeGreaterOrEqualTo.class))
                .append(".")
                .append("message=")
                .append("The provided long value ")
                .append(value)
                .append(" must be greater than or equal to ")
                .append(mustBeGreaterToEqualToValue)
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString())
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
