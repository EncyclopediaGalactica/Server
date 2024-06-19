package com.andrascsanyi.encyclopediagalactica.document.core.scenario;


import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.core.scenarios.AddApplicationScenario;
import com.andrascsanyi.encyclopediagalactica.document.core.scenarios.AddApplicationScenarioException;
import com.andrascsanyi.encyclopediagalactica.document.core.testdata.AddApplicationEntityScenarioInputValidationTestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddApplicationScenarioTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private AddApplicationScenario addApplicationScenario;
    
    @Tag("document")
    @Tag("core")
    @ParameterizedTest
    @ArgumentsSource(AddApplicationEntityScenarioInputValidationTestData.class)
    public void testValidation(ApplicationEntity applicationEntity) {
        assertThatThrownBy(() -> addApplicationScenario.execute(applicationEntity))
            .isInstanceOf(AddApplicationScenarioException.class);
    }
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testAdding() {
        ApplicationEntity entity = ApplicationEntity.builder()
            .id(0L)
            .name("name")
            .description("description").build();
        
        ApplicationEntity result = addApplicationScenario.execute(entity);
        
        assertThat(result.getId()).isGreaterThanOrEqualTo(1);
        assertThat(result.getName()).isEqualTo(entity.getName());
        assertThat(result.getDescription()).isEqualTo(entity.getDescription());
    }
}
