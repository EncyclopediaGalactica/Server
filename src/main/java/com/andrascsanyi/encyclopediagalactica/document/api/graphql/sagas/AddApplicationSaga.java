package com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas;

import com.andrascsanyi.encyclopediagalactica.common.validation.ValidationConstraintsHelper;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.validation.AddApplicationScenario;
import com.andrascsanyi.encyclopediagalactica.document.core.commands.AddApplicationCommand;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AddApplicationSaga {
    
    private AddApplicationCommand addApplicationCommand;
    private ApplicationMapper applicationMapper;
    private Validator validator;
    
    private static final Logger log = LoggerFactory.getLogger(AddApplicationSaga.class);
    
    public ApplicationResponse execute(ApplicationInput input) {
        try {
            validateInput(input);
            Application applicationEntity = applicationMapper.toApplicationEntity(input);
            Application result = addApplicationCommand.addApplication(applicationEntity);
            return applicationMapper.toApplicationOutput(result);
        } catch (Exception e) {
            
            StringBuilder builder = new StringBuilder();
            builder.append("Error happened while executing ")
                .append(AddApplicationSaga.class.getSimpleName());
            
            return DocumentErrorOutput.builder()
                .setMessage(builder.toString())
                .setErrorDetails(e.toString())
                .build();
        }
    }
    
    private void validateInput(ApplicationInput input) {
        Set<ConstraintViolation<Object>> violations = validator.validate(
            input,
            AddApplicationScenario.class);
        if (!violations.isEmpty()) {
            String message = ValidationConstraintsHelper.getViolationsAsString(violations);
            throw new AddApplicationSagaException(message);
        }
    }
}
