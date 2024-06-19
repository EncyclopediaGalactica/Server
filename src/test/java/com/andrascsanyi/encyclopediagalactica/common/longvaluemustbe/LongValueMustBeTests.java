package com.andrascsanyi.encyclopediagalactica.common.longvaluemustbe;

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

@Tag("validator")
@Tag("common")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class LongValueMustBeTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> dataDefaultGroup() {
        return Stream.of(
            Arguments.of(LongValueMustBeDefaultGroupEntity.builder().value(0L).build(), 0),
            Arguments.of(LongValueMustBeDefaultGroupEntity.builder().value(1L).build(), 1),
            Arguments.of(LongValueMustBeDefaultGroupEntity.builder().value(-1L).build(), 1)
        );
    }
    
    public static Stream<Arguments> dataCustomGroup() {
        return Stream.of(
            Arguments.of(LongValueMustBeCustomGroupEntity.builder().value(0L).build(), 0),
            Arguments.of(LongValueMustBeCustomGroupEntity.builder().value(1L).build(), 1),
            Arguments.of(LongValueMustBeCustomGroupEntity.builder().value(-1L).build(), 1)
        );
    }
    
    
    @ParameterizedTest
    @MethodSource("dataDefaultGroup")
    public void testDefaultGroup(LongValueMustBeDefaultGroupEntity entity, Integer expectedErrors) {
        Set<ConstraintViolation<LongValueMustBeDefaultGroupEntity>> constraintViolation = validator.validate(entity);
        
        assertThat(constraintViolation.size())
            .withFailMessage("The provided value is: " + entity.getValue())
            .isEqualTo(expectedErrors);
    }
    
    @ParameterizedTest
    @MethodSource("dataCustomGroup")
    public void testCustomGroup(LongValueMustBeDefaultGroupEntity entity, Integer expectedErrors) {
        Set<ConstraintViolation<LongValueMustBeDefaultGroupEntity>> constraintViolation = validator.validate(
            entity,
            LongValueMustBeValidationGroup.class);
        
        assertThat(constraintViolation.size())
            .withFailMessage("The provided value is: " + entity.getValue())
            .isEqualTo(expectedErrors);
    }
}
