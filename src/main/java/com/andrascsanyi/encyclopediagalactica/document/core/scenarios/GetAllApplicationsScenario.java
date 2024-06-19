package com.andrascsanyi.encyclopediagalactica.document.core.scenarios;

import com.andrascsanyi.encyclopediagalactica.document.core.commands.GetAllApplicationsCommand;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetAllApplicationsScenario {
    
    private final GetAllApplicationsCommand getAllApplicationsCommand;
    private final Logger log = LoggerFactory.getLogger(GetAllApplicationsScenario.class);
    
    public List<ApplicationEntity> execute() {
        try {
            return getAllApplicationsCommand.getAllApplications();
        } catch (Throwable t) {
            log.error("Error while executing getAllApplicationsCommand", t);
            throw new GetAllApplicationsScenarioException(
                "Error while executing getAllApplicationsCommand", t
            );
        }
    }
}
