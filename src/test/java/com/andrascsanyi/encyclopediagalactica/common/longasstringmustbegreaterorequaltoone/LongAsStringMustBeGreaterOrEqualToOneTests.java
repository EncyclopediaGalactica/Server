package com.andrascsanyi.encyclopediagalactica.common.longasstringmustbegreaterorequaltoone;

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
public class LongAsStringMustBeGreaterOrEqualToOneTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testDefaultGroupData() {
        return Stream.of(
            Arguments.of(DefaultGroupEntity.builder().id("0").build(), 1),
            Arguments.of(DefaultGroupEntity.builder().id("1").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id(null).build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id("").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id(" ").build(), 0),
            Arguments.of(DefaultGroupEntity.builder().id("-1").build(), 1)
        );
    }
    
    public static Stream<Arguments> testCustomGroupData() {
        return Stream.of(
            Arguments.of(CustomGroupEntity.builder().id("0").build(), 1),
            Arguments.of(CustomGroupEntity.builder().id("1").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id(null).build(), 0),
            Arguments.of(CustomGroupEntity.builder().id("").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id(" ").build(), 0),
            Arguments.of(CustomGroupEntity.builder().id("-1").build(), 1)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testDefaultGroupData")
    public void testDefaultGroup(DefaultGroupEntity e, long expected) {
        
        assertThat(validator.validate(e).size()).isEqualTo(expected);
    }
    
    @ParameterizedTest
    @MethodSource("testCustomGroupData")
    public void testCustomGroup(CustomGroupEntity e, long expected) {
        
        assertThat(validator.validate(e, CustomGroup.class).size()).isEqualTo(expected);
    }
}
