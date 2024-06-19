package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("validator")
@Tag("common")
public class LongAsStringMustBeZeroTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id("0").build(), 0),
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id("1").build(), 1),
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id(null).build(), 0),
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id("").build(), 0),
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id(" ").build(), 0),
            Arguments.of(LongAsStringMustBeZeroEntity.builder().id("-1").build(), 0)
        );
    }
    
    @Autowired
    private Validator validator;
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(LongAsStringMustBeZeroEntity e, Integer expected) {
        assertThat(validator.validate(e).size()).isGreaterThanOrEqualTo(expected);
    }
}
