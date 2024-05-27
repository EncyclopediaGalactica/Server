package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.common.validator.ValidationException;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddApplicationCommand {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private Validation validation;

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
            throws ValidationException {
        if (applicationInput == null) {
            throw new ValidationException("Application input cannot be null");
        }
    }
}
