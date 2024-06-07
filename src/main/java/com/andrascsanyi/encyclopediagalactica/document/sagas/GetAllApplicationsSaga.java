package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.commands.GetAllApplicationsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllApplicationsSaga {
    
    @Autowired
    private GetAllApplicationsCommand getAllApplicationsCommand;
    
    public List<ApplicationOutput> execute() {
        try {
            return getAllApplicationsCommand.getAllApplications();
        } catch (Exception e) {
            throw new GetallApplicationsSagaException(
                "Error while executing " + GetAllApplicationsSaga.class.getSimpleName(),
                e
            );
        }
    }
}
