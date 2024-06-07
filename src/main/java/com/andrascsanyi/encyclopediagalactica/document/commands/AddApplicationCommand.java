package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.common.validation.ValidationConstraintsHelper;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import com.andrascsanyi.encyclopediagalactica.document.validation.AddApplicationScenario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddApplicationCommand {
    
    private final ApplicationMapper applicationMapper;
    
    private final ApplicationRepository applicationRepository;
    
    private final Validator validator;
    
    @Autowired
    public AddApplicationCommand(
        ApplicationMapper applicationMapper,
        ApplicationRepository applicationRepository,
        Validator validator) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
        this.validator = validator;
    }
    
    public ApplicationOutput addApplication(ApplicationInput applicationInput)
        throws AddApplicationCommandException {
        try {
            validateInputAndThrow(applicationInput);
            ApplicationEntity input = applicationMapper.toApplicationEntity(applicationInput);
            ApplicationEntity applicationEntity = applicationRepository.save(input);
            return applicationMapper.toApplicationOutput(applicationEntity);
        } catch (Throwable e) {
            throw new AddApplicationCommandException(e.getMessage(), e);
        }
    }
    
    private void validateInputAndThrow(ApplicationInput applicationInput)
        throws AddApplicationCommandException {
        if (applicationInput == null) {
            throw new AddApplicationCommandException("Application input is null");
        }
        
        Set<ConstraintViolation<ApplicationInput>> validationResult =
            validator.validate(applicationInput, AddApplicationScenario.class);
        
        if (!validationResult.isEmpty()) {
            String violations = ValidationConstraintsHelper.getViolationsAsString(validationResult);
            throw new AddApplicationCommandException(violations);
        }
    }
}
