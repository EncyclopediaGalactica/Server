package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationListOutputItem;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationListResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.commands.GetAllApplicationsCommand;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllApplicationsSaga {
    
    private GetAllApplicationsCommand getAllApplicationsCommand;
    
    private ApplicationMapper applicationMapper;
    
    public ApplicationListResponse execute() {
        try {
            List<ApplicationEntity> applications = getAllApplicationsCommand.getAllApplications();
            List<ApplicationListOutputItem> applicationListOutputItems = applicationMapper
                .toApplicationListOutputItems(applications);
            return ApplicationListOutput.builder()
                .setApplicationList(applicationListOutputItems).build();
        } catch (Exception e) {
            StringBuilder b = new StringBuilder();
            b.append("Error happened while executing ").append(GetAllApplicationsCommand.class);
            
            return DocumentErrorOutput.builder()
                .setMessage(b.toString())
                .setErrorDetails(e.toString())
                .build();
        }
    }
}
