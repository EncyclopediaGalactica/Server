package com.andrascsanyi.encyclopediagalactica.stepdefs;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.testingtools.ScenarioContext;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class IPostItToTheAddNewApplicationGraphqlEndpointStepDef {
    
    @Autowired
    private ScenarioContext scenarioContext;
    
    @DataTableType
    public IPostItToTheGraphqlEndpointEntity convert(Map<String, String> row) {
        return IPostItToTheGraphqlEndpointEntity
            .builder()
            .inputDataKey(row.get("InputDataKey"))
            .methodName(row.get("MethodName"))
            .outputDataKey(row.get("OutputDataKey"))
            .build();
    }
    
    @When("I post it to the addNewApplication graphql endpoint")
    public void IPostItToTheAddNewApplicationGraphqlEndpoint(@Transpose IPostItToTheGraphqlEndpointEntity data) {
        
        ApplicationEntity payload = (ApplicationEntity) scenarioContext.get(data.getInputDataKey());
        
        WebTestClient client = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:8080/graphql")
            .build();
        HttpGraphQlTester tester = HttpGraphQlTester.create(client);
        
        ApplicationOutput result = tester.document(
                """
                    mutation createApplication {
                        createApplication(application: ApplicationInput!) {
                            id
                            name
                            description
                        }
                    }
                    """
            ).execute()
            .path("data")
            .entity(ApplicationOutput.class)
            .get();
        
    }
}
