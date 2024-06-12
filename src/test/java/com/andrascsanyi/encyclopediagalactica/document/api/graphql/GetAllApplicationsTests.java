package com.andrascsanyi.encyclopediagalactica.document.api.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GetAllApplicationsTests extends EncyclopediaGalacticaApplicationBaseTest {

    @Autowired
    private HttpGraphQlTester tester;

    @Test
    public void testWhenTheDatabaseIsEmpty() {

        tester.documentName("document/getApplications")
                .execute()
                .path("data.getApplications")
                .entity(ApplicationResponse.class)
                .satisfies(
                        result -> {
                            assertThat(result).isInstanceOf(ApplicationResponse.class);
                            ApplicationListOutput r = (ApplicationListOutput) result;
                            assertThat(r.getApplicationList()).isEmpty();
                        });
    }

    @Test
    public void testWhenTheDatabaseHasMultipleItems() {
        for (int i = 0; i < 2; i++) {
            ApplicationInput input = ApplicationInput.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .build();

            tester.documentName("document/addApplication").variable("input", input).execute();
        }

        tester.documentName("document/getApplications")
                .execute()
                .path("data.getApplications")
                .entity(ApplicationResponse.class)
                .satisfies(
                        result -> {
                            assertThat(result).isInstanceOf(ApplicationResponse.class);
                            ApplicationListOutput r = (ApplicationListOutput) result;
                            assertThat(r.getApplicationList().size()).isEqualTo(2);
                        });
    }
}
