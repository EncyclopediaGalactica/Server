package com.andrascsanyi.encyclopediagalactica.common.longvaluemustbeequalorgreaterto;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
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

@Tag("common")
@Tag("validator")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class LongValueMustBeEqualOrGreaterToTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> dataDefaultGroup() {
        return Stream.of(
            Arguments.of(
                DefaultGroupEntity.builder().value(0L).build(),
                1),
            Arguments.of(
                DefaultGroupEntity.builder().value(1L).build(),
                0),
            Arguments.of(
                DefaultGroupEntity.builder().value(2L).build(),
                0)
        );
    }
    
    public static Stream<Arguments> dataCustomGroup() {
        return Stream.of(
            Arguments.of(
                CustomGroupEntity.builder().value(0L).build(),
                1),
            Arguments.of(
                CustomGroupEntity.builder().value(1L).build(),
                0),
            Arguments.of(
                CustomGroupEntity.builder().value(2L).build(),
                0)
        );
    }
    
    @ParameterizedTest
    @MethodSource("dataDefaultGroup")
    public void testDefaultGroup(DefaultGroupEntity entity, int expectedGroup) {
        Set<ConstraintViolation<DefaultGroupEntity>> result = validator.validate(
            entity
        );
        
        assertThat(result.size())
            .withFailMessage("Provided value is: " + entity.getValue())
            .isEqualTo(expectedGroup);
    }
    
    @ParameterizedTest
    @MethodSource("dataCustomGroup")
    public void testCustomGroup(CustomGroupEntity entity, int expectedGroup) {
        Set<ConstraintViolation<CustomGroupEntity>> result = validator.validate(
            entity,
            CustomGroup.class
        );
        
        assertThat(result.size())
            .withFailMessage("Provided value is: " + entity.getValue())
            .isEqualTo(expectedGroup);
    }
}
