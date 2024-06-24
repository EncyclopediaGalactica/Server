package com.andrascsanyi.encyclopediagalactica.document.api.sagas;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas.AddApplicationSaga;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas.GetAllApplicationsSaga;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetAllApplicationsSagaTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private GetAllApplicationsSaga getAllApplicationsSaga;
    
    @Autowired
    private AddApplicationSaga addApplicationSaga;
    
    @Test
    public void testWhenTheDatabaseIsEmpty() {
        ApplicationListResponse result = getAllApplicationsSaga.execute();
        assertThat(result).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput r = (ApplicationListOutput) result;
        assertThat(r.getApplicationList()).isEmpty();
    }
    
    @Test
    public void testWhenThereIsOnlyOneItemInTheDatabase() {
        ApplicationInput input = ApplicationInput.builder()
            .setName("name")
            .setDescription("description")
            .build();
        ApplicationResponse recordingResult = addApplicationSaga.execute(input);
        assertThat(recordingResult).isInstanceOf(ApplicationOutput.class);
        
        ApplicationListResponse r = getAllApplicationsSaga.execute();
        
        assertThat(r).isInstanceOf(ApplicationListOutput.class);
        assertThat(r).isNotInstanceOf(DocumentErrorOutput.class);
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
            ApplicationInput input = ApplicationInput.builder()
                .setName("name" + i)
                .setDescription("description" + i)
                .build();
            ApplicationResponse recordingResult = addApplicationSaga.execute(input);
            assertThat(recordingResult).isInstanceOf(ApplicationOutput.class);
        }
        
        ApplicationListResponse r = getAllApplicationsSaga.execute();
        assertThat(r).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput res = (ApplicationListOutput) r;
        
        assertThat(res.getApplicationList()).isNotEmpty();
        assertThat(res.getApplicationList().size()).isEqualTo(2);
    }
}
