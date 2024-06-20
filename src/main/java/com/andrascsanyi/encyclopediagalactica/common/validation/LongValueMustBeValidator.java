package com.andrascsanyi.encyclopediagalactica.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.andrascsanyi.encyclopediagalactica.common.validation.ValidationHelpers.getPackageAndAnnotationName;

public class LongValueMustBeValidator implements ConstraintValidator<LongValueMustBe, Long> {
    private Long mustBe;
    
    @Override
    public void initialize(LongValueMustBe constraintAnnotation) {
        mustBe = constraintAnnotation.mustBe();
    }
    
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!value.equals(mustBe)) {
            StringBuilder builder = new StringBuilder();
            builder
                .append("{")
                .append(getPackageAndAnnotationName(LongValueMustBe.class))
                .append(".")
                .append("message=")
                .append("The provided Long value (")
                .append(value)
                .append(")")
                .append(" has to be equal to ")
                .append(mustBe)
                .append("}");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(builder.toString())
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
