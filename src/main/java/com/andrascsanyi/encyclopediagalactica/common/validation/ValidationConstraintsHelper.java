package com.andrascsanyi.encyclopediagalactica.common.validation;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationInput;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public final class ValidationConstraintsHelper {
    public static String getViolationsAsString(Set<ConstraintViolation<ApplicationInput>> validationResult) {
        StringBuilder stringBuilder = new StringBuilder();
        validationResult.forEach(i -> {
            stringBuilder.append(i.getMessage()).append(" ");
        });
        return stringBuilder.toString();
    }
}
