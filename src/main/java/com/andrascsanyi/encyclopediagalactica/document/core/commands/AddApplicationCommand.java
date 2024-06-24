package com.andrascsanyi.encyclopediagalactica.document.core.commands;

import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import com.andrascsanyi.encyclopediagalactica.document.core.repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddApplicationCommand {
    
    private final ApplicationRepository applicationRepository;
    
    public Application addApplication(Application applicationEntityInput)
        throws AddApplicationCommandException {
        try {
            Application applicationEntity = applicationRepository.save(applicationEntityInput);
            return applicationEntity;
        } catch (Throwable e) {
            throw new AddApplicationCommandException(e.getMessage(), e);
        }
    }
}
