package com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListOutputItem;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.core.commands.GetAllApplicationsCommand;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
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
