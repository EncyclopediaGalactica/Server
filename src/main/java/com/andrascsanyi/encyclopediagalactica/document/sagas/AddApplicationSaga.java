package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.common.validation.ValidationConstraintsHelper;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.commands.AddApplicationCommand;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.validation.AddApplicationScenario;
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
            ApplicationEntity applicationEntity = applicationMapper.toApplicationEntity(input);
            ApplicationEntity result = addApplicationCommand.addApplication(applicationEntity);
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
        Set<ConstraintViolation<ApplicationInput>> violations = validator.validate(
            input,
            AddApplicationScenario.class);
        if (!violations.isEmpty()) {
            String message = ValidationConstraintsHelper.getViolationsAsString(violations);
            throw new AddApplicationSagaException(message);
        }
    }
}
