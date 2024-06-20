package com.andrascsanyi.encyclopediagalactica.document.core.scenarios;

import com.andrascsanyi.encyclopediagalactica.common.validation.ValidationConstraintsHelper;
import com.andrascsanyi.encyclopediagalactica.document.core.commands.AddApplicationCommand;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import com.andrascsanyi.encyclopediagalactica.document.core.validation.AddApplicationEntityScenario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AddApplicationScenario {
    
    private final AddApplicationCommand addApplicationCommand;
    private final Validator validator;
    private final Logger log = LoggerFactory.getLogger(AddApplicationScenario.class);
    
    public Application execute(Application applicationEntity) {
        
        try {
            validateInput(applicationEntity);
            return addApplicationCommand.addApplication(applicationEntity);
        } catch (Throwable t) {
            
            String errorMessage = String.format("Failed to add application: %s", applicationEntity);
            log.error(errorMessage, t);
            throw new AddApplicationScenarioException(errorMessage, t);
        }
    }
    
    private void validateInput(Application applicationEntity) {
        Set<ConstraintViolation<Object>> violations = validator.validate(
            applicationEntity,
            AddApplicationEntityScenario.class);
        if (!violations.isEmpty()) {
            String error = ValidationConstraintsHelper.getViolationsAsString(violations);
            throw new AddApplicationScenarioException(error);
        }
    }
}
