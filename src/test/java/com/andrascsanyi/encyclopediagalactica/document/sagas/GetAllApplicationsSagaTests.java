package com.andrascsanyi.encyclopediagalactica.document.sagas;

import static org.assertj.core.api.Assertions.assertThat;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetAllApplicationsSagaTests extends EncyclopediaGalacticaApplicationBaseTest {

    @Autowired private GetAllApplicationsSaga getAllApplicationsSaga;

    @Autowired private AddApplicationSaga addApplicationSaga;

    @Test
    public void testWhenTheDatabaseIsEmpty() {
        ApplicationResponse result = getAllApplicationsSaga.execute();
        assertThat(result).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput r = (ApplicationListOutput) result;
        assertThat(r.getApplicationList()).isEmpty();
    }

    @Test
    public void testWhenThereIsOnlyOneItemInTheDatabase() {
        ApplicationInput input =
                ApplicationInput.builder().name("name").description("description").build();
        ApplicationResponse recordingResult = addApplicationSaga.execute(input);
        assertThat(recordingResult).isInstanceOf(ApplicationOutput.class);

        ApplicationResponse r = getAllApplicationsSaga.execute();

        assertThat(r).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput res = (ApplicationListOutput) r;

        assertThat(res.getApplicationList()).isNotEmpty();
        assertThat(res.getApplicationList().size()).isEqualTo(1);
        assertThat(res.getApplicationList().get(0).getId()).isNotEqualTo("0");
        assertThat(res.getApplicationList().get(0).getName()).isEqualTo(input.getName());
        assertThat(res.getApplicationList().get(0).getDescription())
                .isEqualTo(input.getDescription());
    }

    @Test
    public void testWhenThereAreMultipleItemsIntTheDatabase() {
        for (int i = 0; i < 2; i++) {
            ApplicationInput input =
                    ApplicationInput.builder()
                            .name("name" + i)
                            .description("description" + i)
                            .build();
            ApplicationResponse recordingResult = addApplicationSaga.execute(input);
            assertThat(recordingResult).isInstanceOf(ApplicationOutput.class);
        }

        ApplicationResponse r = getAllApplicationsSaga.execute();
        assertThat(r).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput res = (ApplicationListOutput) r;

        assertThat(res.getApplicationList()).isNotEmpty();
        assertThat(res.getApplicationList().size()).isEqualTo(2);
    }
}
