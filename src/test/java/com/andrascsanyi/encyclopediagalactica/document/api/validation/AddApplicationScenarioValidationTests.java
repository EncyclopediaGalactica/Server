package com.andrascsanyi.encyclopediagalactica.document.api.validation;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.scenarios.AddApplicationScenario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationScenarioValidationTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            // id
            Arguments.of(ApplicationInput.builder().setId("").setName("asd").setDescription("asd").build(), 0),
            Arguments.of(ApplicationInput.builder().setId(" ").setName("asd").setDescription("asd").build(), 0),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("asd").build(), 0),
            Arguments.of(ApplicationInput.builder().setId("1").setName("asd").setDescription("asd").build(), 1),
            // name
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("asd").build(), 0),
            Arguments.of(ApplicationInput.builder().setId("0").setName("").setDescription("asd").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a").setDescription("asd").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("as").setDescription("asd").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("as ").setDescription("asd").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName(" as").setDescription("asd").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("   ").setDescription("asd").build(), 1),
            // description
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("asd").build(), 0),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("a").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("as").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("as ").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription(" as").build(), 1),
            Arguments.of(ApplicationInput.builder().setId("0").setName("asd").setDescription("   ").build(), 1));
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(ApplicationInput applicationInput, Integer errorVolume) {
        Set<ConstraintViolation<ApplicationInput>> constraintViolations = validator.validate(
            applicationInput,
            AddApplicationScenario.class);
        
        StringBuilder builder = new StringBuilder();
        builder.append("id: -").append(applicationInput.getId()).append("-").append("\n");
        builder.append("name: -").append(applicationInput.getName()).append("-").append("\n");
        builder.append("description: -").append(applicationInput.getDescription()).append("-").append("\n");
        
        assertThat(constraintViolations.size())
            .withFailMessage(builder.toString())
            .isGreaterThanOrEqualTo(errorVolume);
    }
}
