package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.DocumentErrorOutput;
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
            return ApplicationListOutput.builder().applicationList(applications).build();
        } catch (Exception e) {
            StringBuilder b = new StringBuilder();
            b.append("Error happened while executing ").append(GetAllApplicationsCommand.class);

            return DocumentErrorOutput.builder()
                    .message(b.toString())
                    .errorDetails(e.toString())
                    .build();
        }
    }
}
