package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import jakarta.validation.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LongAsStringMustBeGreaterOrEqualToOneTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id("0").build(), 1),
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id("1").build(), 0),
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id(null).build(), 0),
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id("").build(), 0),
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id(" ").build(), 0),
            Arguments.of(LongAsStringMustBeGreaterOrEqualToOneEntity.builder().id("-1").build(), 1)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(LongAsStringMustBeGreaterOrEqualToOneEntity e, long expected) {
        
        assertThat(validator.validate(e).size()).isEqualTo(expected);
    }
}
