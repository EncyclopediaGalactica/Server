package com.andrascsanyi.encyclopediagalactica.document.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.andrascsanyi.encyclopediagalactica.document.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.sagas.AddApplicationSaga;
import com.andrascsanyi.encyclopediagalactica.document.sagas.GetAllApplicationsSaga;

@Controller
public class ApplicationController {

    @Autowired
    private AddApplicationSaga addApplicationSaga;
    
    @Autowired
    private GetAllApplicationsSaga getAllApplicationsSaga;

    private final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @MutationMapping("addApplication")
    public ApplicationOutput addApplication(
            @Argument(name = "application") ApplicationInput applicationInput) {
        log.info(applicationInput.toString());
        return addApplicationSaga.execute(applicationInput);
    }

    @QueryMapping("getApplications")
    public List<ApplicationOutput> getApplications() {
        return getAllApplicationsSaga.execute();
    }
}
