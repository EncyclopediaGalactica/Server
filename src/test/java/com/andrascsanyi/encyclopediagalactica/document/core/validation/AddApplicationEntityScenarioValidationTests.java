package com.andrascsanyi.encyclopediagalactica.document.core.validation;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.core.testdata.AddApplicationEntityScenarioInputValidationTestData;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("document")
@Tag("core")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddApplicationEntityScenarioValidationTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    @ParameterizedTest
    @ArgumentsSource(AddApplicationEntityScenarioInputValidationTestData.class)
    public void testValidation(ApplicationEntity applicationEntity) {
        Set<ConstraintViolation<ApplicationEntity>> violations = validator.validate(
            applicationEntity,
            AddApplicationEntityScenario.class);
        
        assertThat(violations.size()).isEqualTo(1);
    }
}
