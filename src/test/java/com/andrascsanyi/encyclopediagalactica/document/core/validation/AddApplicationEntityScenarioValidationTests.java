package com.andrascsanyi.encyclopediagalactica.document.core.validation;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("document")
@Tag("core")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddApplicationEntityScenarioValidationTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(Application.builder().id(1L).name("asd").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name(null).description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\t").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\r").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\n").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("as").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("as ").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name(" as ").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description(null).build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\t").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\r").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\n").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("as ").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description(" as ").build())
        );
    }
    
    @ParameterizedTest
    @MethodSource("data")
    public void testValidation(Application applicationEntity) {
        Set<ConstraintViolation<Application>> violations = validator.validate(
            applicationEntity,
            AddApplicationEntityScenario.class);
        
        StringBuilder builder = new StringBuilder();
        builder
            .append("| Property Name | Property value | \n")
            .append("|-------|--------------|\n")
            .append("|id |").append(applicationEntity.getId()).append("| \n")
            .append("|name: |").append(applicationEntity.getName()).append("| \n")
            .append("|desc: |").append(applicationEntity.getDescription()).append("| \n")
            .append("|----------|---------------|\n")
            .append("|Constraint violations| \n");
        
        if (!violations.isEmpty()) {
            violations.forEach(i -> {
                builder.append("|").append(i.getMessage()).append("|\n");
            });
        }
        
        assertThat(violations.size())
            .withFailMessage(builder.toString())
            .isGreaterThanOrEqualTo(1);
    }
}
