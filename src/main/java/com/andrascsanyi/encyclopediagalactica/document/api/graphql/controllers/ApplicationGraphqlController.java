package com.andrascsanyi.encyclopediagalactica.document.api.graphql.controllers;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas.AddApplicationSaga;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas.GetAllApplicationsSaga;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ApplicationGraphqlController {
    
    private AddApplicationSaga addApplicationSaga;
    private GetAllApplicationsSaga getAllApplicationsSaga;
    
    private final Logger log = LoggerFactory.getLogger(ApplicationGraphqlController.class);
    
    @MutationMapping("addApplication")
    public ApplicationResponse addApplication(
        @Argument(name = "application") ApplicationInput applicationInput) {
        log.info(applicationInput.toString());
        return addApplicationSaga.execute(applicationInput);
    }
    
    @QueryMapping("getApplications")
    public ApplicationListResponse getApplications() {
        return getAllApplicationsSaga.execute();
    }
}
