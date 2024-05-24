package com.andrascsanyi.encyclopediagalactica.document.validators;

import com.andrascsanyi.encyclopediagalactica.common.validator.ConstraintViolation;
import com.andrascsanyi.encyclopediagalactica.common.validator.StringValidator;
import com.andrascsanyi.encyclopediagalactica.common.validator.Validator;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AddApplicationSagaInputValidator implements Validator<ApplicationInput> {
    
    private final StringValidator stringValidator;
    
    private List<ConstraintViolation> violations = new ArrayList<>();
    
    private Map<String, String> rules = Map.of(
        "id", "When recording Application the id must be zero",
        "name", "Application name length must be between 1-3",
        "description", "Application name length must be between 1-3"
    );
    
    public AddApplicationSagaInputValidator(
        StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }
    
    @Override
    public boolean validate(final ApplicationInput input) {
        validateId(input.getId());
        validateName(input.getName());
        validateDescription(input.getDescription());
        return violations.isEmpty();
    }
    
    @Override
    public String getViolationsForException() {
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder("Violations: ");
            violations.forEach(i -> {
                builder.append(i.getViolationMessage()).append(" ");
            });
            return builder.toString();
        }
        return "";
    }
    
    private void validateId(final String l) {
        if (!stringValidator.isNull(l) && !stringValidator.isEmpty(l)
            && !stringValidator.isWhitespace(l) && !stringValidator.isZero(l)) {
            violations.add(
                ConstraintViolation.builder()
                    .rule(rules.get("id"))
                    .violationMessage("Application Id must be zero")
                    .build());
        }
    }
    
    private void validateName(final String s) {
        if (stringValidator.isNull(s)) {
            violations.add(
                ConstraintViolation.builder()
                    .rule("name")
                    .violationMessage("Name parameter is null")
                    .build()
            );
        }
        
        if (!stringValidator.isNull(s)
            && (stringValidator.isWhitespace(s) || stringValidator.isEmpty(s))) {
            violations.add(
                ConstraintViolation.builder()
                    .rule("name")
                    .violationMessage("Name parameter is empty or whitespace")
                    .build()
            );
        }
        
        if (!stringValidator.isNull(s) && !stringValidator.isEmpty(s) && !stringValidator.isWhitespace(s) &&
            !stringValidator.isTrimmedLengthGreaterOrEqualTo(s, 3)) {
            violations.add(
                ConstraintViolation.builder()
                    .rule(rules.get("name"))
                    .violationMessage("Application name trimmed length is not equal or greater than 3")
                    .build()
            );
        }
    }
    
    private void validateDescription(final String s) {
        if (stringValidator.isNull(s)) {
            violations.add(
                ConstraintViolation.builder()
                    .rule("description")
                    .violationMessage("Description parameter is null")
                    .build()
            );
        }
        
        if (!stringValidator.isNull(s)
            && (stringValidator.isWhitespace(s) || stringValidator.isEmpty(s))) {
            violations.add(
                ConstraintViolation.builder()
                    .rule("description")
                    .violationMessage("Description parameter is empty or whitespace")
                    .build()
            );
        }
        
        if (!stringValidator.isNull(s) && !stringValidator.isEmpty(s) && !stringValidator.isWhitespace(s)
            && !stringValidator.isTrimmedLengthGreaterOrEqualTo(s, 3)) {
            violations.add(
                ConstraintViolation.builder()
                    .rule(rules.get("description"))
                    .violationMessage("Application description trimmed length is not equal or greater than 3")
                    .build()
            );
        }
    }
}
