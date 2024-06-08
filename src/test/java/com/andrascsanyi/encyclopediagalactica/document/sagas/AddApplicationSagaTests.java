package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationListOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.testdata.AddApplicationScenarioInputValidationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationSagaTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private AddApplicationSaga addApplicationSaga;
    
    @Autowired
    private GetAllApplicationsSaga getAllApplicationsSaga;
    
    @ParameterizedTest
    @ArgumentsSource(AddApplicationScenarioInputValidationData.class)
    public void inputValidation(ApplicationInput input) {
        
        ApplicationResponse response = addApplicationSaga.execute(input);
        assertThat(response).isInstanceOf(DocumentErrorOutput.class);
    }
    
    @Test
    public void createApplication() {
        
        ApplicationInput input = ApplicationInput.builder().name("name").description("description").build();
        ApplicationResponse res = addApplicationSaga.execute(input);
        assertThat(res).isInstanceOf(ApplicationOutput.class);
        
        ApplicationResponse result = getAllApplicationsSaga.execute();
        
        assertThat(result).isInstanceOf(ApplicationListOutput.class);
        ApplicationListOutput r = (ApplicationListOutput) result;
        assertThat(r.getApplicationList()).isNotEmpty();
        assertThat(r.getApplicationList().size()).isEqualTo(1);
        assertThat(r.getApplicationList().get(0).getId()).isNotEqualTo("0");
        assertThat(r.getApplicationList().get(0).getName()).isEqualTo(input.getName());
        assertThat(r.getApplicationList().get(0).getDescription()).isEqualTo(input.getDescription());
    }
}
