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
import com.andrascsanyi.encyclopediagalactica.document.api.testdata.AddApplicationViaGraphqlApiScenarioInputValidationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationSagaTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private AddApplicationSaga addApplicationSaga;
    
    @Autowired
    private GetAllApplicationsSaga getAllApplicationsSaga;
    
    @ParameterizedTest
    @ArgumentsSource(AddApplicationViaGraphqlApiScenarioInputValidationData.class)
    public void inputValidation(ApplicationInput input) {
        
        ApplicationResponse response = addApplicationSaga.execute(input);
        assertThat(response).isInstanceOf(DocumentErrorOutput.class);
    }
    
    @Test
    public void createApplication() {
        
        ApplicationInput input = ApplicationInput.builder()
            .setId("0")
            .setName("name")
            .setDescription("description")
            .build();
        ApplicationResponse res = addApplicationSaga.execute(input);
        assertThat(res).isInstanceOf(ApplicationOutput.class);
        
        ApplicationListResponse result = getAllApplicationsSaga.execute();
        
        assertThat(result).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput r = (ApplicationListOutput) result;
        assertThat(r.getApplicationList()).isNotEmpty();
        assertThat(r.getApplicationList().size()).isEqualTo(1);
        assertThat(r.getApplicationList().get(0).getId()).isNotEqualTo("0");
        assertThat(r.getApplicationList().get(0).getName()).isEqualTo(input.getName());
        assertThat(r.getApplicationList().get(0).getDescription()).isEqualTo(input.getDescription());
    }
}
