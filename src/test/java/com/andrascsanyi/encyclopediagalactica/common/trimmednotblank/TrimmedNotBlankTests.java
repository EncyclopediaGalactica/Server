package com.andrascsanyi.encyclopediagalactica.common.trimmednotblank;

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
public class TrimmedNotBlankTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testDataDefaultGroup() {
        return Stream.of(
            Arguments.of(DefaultGroupEntity.builder().id("").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id("  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id("   ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" \t  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" \n  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" \r  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" \f  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id(" \s  ").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id("a").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id("a ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id("a  ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id(" a  ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id("  a  ").build(), 0)
        );
    }
    
    public static Stream<Arguments> testDataCustomGroup() {
        return Stream.of(
            Arguments.of(CustomGroupEntity.builder().id("").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id("  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id("   ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" \t  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" \n  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" \r  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" \f  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id(" \s  ").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id("a").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id("a ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id("a  ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id(" a  ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id("  a  ").build(), 0)
        );
    }
    
    
    @ParameterizedTest
    @MethodSource("testDataDefaultGroup")
    public void testDefaultGroup(DefaultGroupEntity input, int expected) {
        
        assertThat(validator.validate(input).size())
            .withFailMessage("string value: -" + input.getId() + "-")
            .isGreaterThanOrEqualTo(expected);
    }
    
    @ParameterizedTest
    @MethodSource("testDataCustomGroup")
    public void testCustomGroup(CustomGroupEntity input, int expected) {
        
        assertThat(validator.validate(input, CustomGroup.class).size())
            .withFailMessage("string value: -" + input.getId() + "-")
            .isGreaterThanOrEqualTo(expected);
    }
}
