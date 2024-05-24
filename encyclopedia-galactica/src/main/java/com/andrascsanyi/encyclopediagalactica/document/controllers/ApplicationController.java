package com.andrascsanyi.encyclopediagalactica.document.controllers;

import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.sagas.IHaveInputOutputSaga;
import com.andrascsanyi.encyclopediagalactica.document.sagas.IHaveOutputSaga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ApplicationController {
    
    private final IHaveInputOutputSaga<ApplicationInput, ApplicationOutput> addApplicationSaga;
    private final IHaveOutputSaga<List<ApplicationOutput>> getAllApplicationsSaga;
    private final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    
    public ApplicationController(
        IHaveInputOutputSaga<ApplicationInput, ApplicationOutput> addApplicationSaga,
        IHaveOutputSaga<List<ApplicationOutput>> getAllApplicationsSaga) {
        this.addApplicationSaga = addApplicationSaga;
        this.getAllApplicationsSaga = getAllApplicationsSaga;
    }
    
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
