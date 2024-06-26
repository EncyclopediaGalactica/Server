package com.andrascsanyi.encyclopediagalactica.document.api.graphql;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.testdata.AddApplicationViaGraphqlApiScenarioInputValidationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationGqlApiTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private HttpGraphQlTester graphQlTester;
    
    @Test
    public void addEntity() {
        
        ApplicationInput input = ApplicationInput.builder()
            .setId("0")
            .setName("test")
            .setDescription("test")
            .build();
        
        graphQlTester.documentName("document/addApplication")
            .variable("input", input)
            .execute()
            .path("data.addApplication")
            .entity(ApplicationResponse.class)
            .satisfies(result -> {
                assertThat(result).isInstanceOf(ApplicationResponse.class);
                ApplicationOutput r = (ApplicationOutput) result;
                assertThat(r.getId()).isNotEqualTo("0").isNotBlank().isNotEmpty();
                assertThat(r.getName()).isEqualTo(input.getName());
                assertThat(r.getDescription()).isEqualTo(input.getDescription());
            });
    }
    
    @ParameterizedTest
    @ArgumentsSource(AddApplicationViaGraphqlApiScenarioInputValidationData.class)
    public void addEntityInputValidation(ApplicationInput data) {
        
        graphQlTester.documentName("document/addApplication")
            .variable("input", data)
            .execute()
            .errors()
            .satisfy(error -> {
                error.forEach(i -> {
                    System.out.println(i.getMessage());
                    System.out.println(i.getErrorType());
                });
            });
    }
}
