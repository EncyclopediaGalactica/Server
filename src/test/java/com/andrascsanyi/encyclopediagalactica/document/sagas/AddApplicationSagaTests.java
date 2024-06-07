package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
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
        ApplicationResponse r = addApplicationSaga.execute(input);
        assertThat(r).isInstanceOf(ApplicationOutput.class);
        
        List<ApplicationOutput> result = getAllApplicationsSaga.execute();
        
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isNotEqualTo("0");
        assertThat(result.get(0).getName()).isEqualTo(input.getName());
        assertThat(result.get(0).getDescription()).isEqualTo(input.getDescription());
    }
}
