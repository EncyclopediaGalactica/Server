package com.andrascsanyi.encyclopediagalactica.document.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import jakarta.validation.Validation;

@Service
public class AddApplicationCommand {

    private final ApplicationMapper applicationMapper;

    private final ApplicationRepository applicationRepository;

    private final Validation validation;

    @Autowired
    public AddApplicationCommand(ApplicationMapper applicationMapper,
            ApplicationRepository applicationRepository, Validation validation) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
        this.validation = validation;
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

    private void validateInputAndThrow(ApplicationInput applicationInput) {}
}
