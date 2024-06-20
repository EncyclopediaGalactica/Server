package com.andrascsanyi.encyclopediagalactica.common.trimmedsize;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("validator")
@Tag("common")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TrimmedSizeTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testDefaultGroupData() {
        return Stream.of(
            // min
            Arguments.of(DefaultGroupEntity.builder().value("qw").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().value("qw ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().value(" qw").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().value(" qw ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().value("qww").build(), 0),
            // max
            Arguments.of(DefaultGroupEntity.builder().value("qwwww").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().value("qwwww ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().value(" qwwww").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().value(" qwwww ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().value("qwwwww").build(), 1)
        );
    }
    
    public static Stream<Arguments> testCustomGroupData() {
        return Stream.of(
            // min
            Arguments.of(CustomGroupEntity.builder().value("qw").build(), 1),
            Arguments.of(CustomGroupEntity.builder().value("qw ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().value(" qw").build(), 1),
            Arguments.of(CustomGroupEntity.builder().value(" qw ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().value("qww").build(), 0),
            // max
            Arguments.of(CustomGroupEntity.builder().value("qwwww").build(), 0),
            Arguments.of(CustomGroupEntity.builder().value("qwwww ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().value(" qwwww").build(), 0),
            Arguments.of(CustomGroupEntity.builder().value(" qwwww ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().value("qwwwww").build(), 1)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testDefaultGroupData")
    public void testDefaultGroup(DefaultGroupEntity input, int expectedSize) {
        
        assertThat(validator.validate(input).size())
            .withFailMessage("value: -" + input.getValue() + "-")
            .isGreaterThanOrEqualTo(expectedSize);
    }
    
    @ParameterizedTest
    @MethodSource("testCustomGroupData")
    public void testCustomGroup(CustomGroupEntity input, int expectedSize) {
        
        assertThat(validator.validate(input, CustomGroup.class).size())
            .withFailMessage("value: -" + input.getValue() + "-")
            .isGreaterThanOrEqualTo(expectedSize);
    }
}
