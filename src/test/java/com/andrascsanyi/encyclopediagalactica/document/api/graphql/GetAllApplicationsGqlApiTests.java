package com.andrascsanyi.encyclopediagalactica.document.api.graphql;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GetAllApplicationsGqlApiTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private HttpGraphQlTester tester;
    
    @Test
    public void testWhenTheDatabaseIsEmpty() {
        
        tester.documentName("document/getApplications")
            .execute()
            .path("data.getApplications")
            .entity(ApplicationListResponse.class)
            .satisfies(
                result -> {
                    assertThat(result).isInstanceOf(ApplicationListResponse.class);
                    ApplicationListOutput r = (ApplicationListOutput) result;
                    assertThat(r.getApplicationList()).isEmpty();
                });
    }
    
    @Test
    public void testWhenTheDatabaseHasMultipleItems() {
        for (int i = 0; i < 2; i++) {
            ApplicationInput input = ApplicationInput.builder()
                .setId("0")
                .setName("name" + i)
                .setDescription("description" + i)
                .build();
            
            tester.documentName("document/addApplication")
                .variable("input", input)
                .execute()
                .path("data.addApplication")
                .entity(ApplicationResponse.class)
                .satisfies(result -> {
                    assertThat(result).isInstanceOf(ApplicationResponse.class);
                    ApplicationOutput r = (ApplicationOutput) result;
                    assertThat(r.getName()).isEqualTo(input.getName());
                    assertThat(r.getDescription()).isEqualTo(input.getDescription());
                });
        }
        
        tester.documentName("document/getApplications")
            .execute()
            .path("data.getApplications")
            .entity(ApplicationListResponse.class)
            .satisfies(
                result -> {
                    assertThat(result).isInstanceOf(ApplicationListResponse.class);
                    ApplicationListOutput r = (ApplicationListOutput) result;
                    assertThat(r.getApplicationList().size()).isEqualTo(2);
                });
    }
}
