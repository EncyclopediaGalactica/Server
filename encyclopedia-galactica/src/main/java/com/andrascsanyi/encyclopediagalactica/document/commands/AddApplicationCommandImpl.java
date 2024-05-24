package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.common.validator.ValidationException;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import com.andrascsanyi.encyclopediagalactica.document.validators.AddApplicationSagaInputValidator;
import org.springframework.stereotype.Service;

@Service
public class AddApplicationCommandImpl implements AddApplicationCommand {
    
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    private final AddApplicationSagaInputValidator addApplicationSagaInputValidator;
    
    public AddApplicationCommandImpl(
        ApplicationMapper applicationMapper,
        ApplicationRepository applicationRepository,
        AddApplicationSagaInputValidator addApplicationSagaInputValidator) {
        
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
        this.addApplicationSagaInputValidator = addApplicationSagaInputValidator;
    }
    
    @Override
    public ApplicationOutput addApplication(ApplicationInput applicationInput) throws AddApplicationCommandException {
        try {
            validateInputAndThrow(applicationInput);
            ApplicationEntity input = applicationMapper.toApplicationEntity(applicationInput);
            ApplicationEntity applicationEntity = applicationRepository.save(input);
            return applicationMapper.toApplicationOutput(applicationEntity);
        } catch (Throwable e) {
            throw new AddApplicationCommandException(e.getMessage(), e);
        }
    }
    
    private void validateInputAndThrow(ApplicationInput applicationInput) throws ValidationException {
        if (applicationInput == null) {
            throw new ValidationException("Application input cannot be null");
        }
        
        if (!addApplicationSagaInputValidator.validate(applicationInput)) {
            throw new ValidationException(addApplicationSagaInputValidator.getViolationsForException());
        }
    }
}
