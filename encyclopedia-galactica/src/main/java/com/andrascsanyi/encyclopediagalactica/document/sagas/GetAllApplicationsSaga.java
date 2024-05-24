package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.commands.GetAllApplicationsCommand;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllApplicationsSaga implements IHaveOutputSaga<List<ApplicationOutput>> {
    
    private final GetAllApplicationsCommand getAllApplicationsCommand;
    
    public GetAllApplicationsSaga(GetAllApplicationsCommand getAllApplicationsCommand) {
        this.getAllApplicationsCommand = getAllApplicationsCommand;
    }
    
    @Override
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
