package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.commands.GetAllApplicationsCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllApplicationsSaga {

    @Autowired private GetAllApplicationsCommand getAllApplicationsCommand;

    public ApplicationResponse execute() {
        try {
            List<ApplicationOutput> applications = getAllApplicationsCommand.getAllApplications();
            return ApplicationListOutput.builder().setApplicationList(applications).build();
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
