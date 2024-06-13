package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddApplicationCommand {
    
    private final ApplicationRepository applicationRepository;
    
    public ApplicationEntity addApplication(ApplicationEntity applicationEntityInput)
        throws AddApplicationCommandException {
        try {
            ApplicationEntity applicationEntity = applicationRepository.save(applicationEntityInput);
            return applicationEntity;
        } catch (Throwable e) {
            throw new AddApplicationCommandException(e.getMessage(), e);
        }
    }
}
