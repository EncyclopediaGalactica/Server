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
public class TrimmedSizeTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            // min
            Arguments.of(TrimmedSizeEntity.builder().value("qw").build(), 1),
            Arguments.of(TrimmedSizeEntity.builder().value("qw ").build(), 1),
            Arguments.of(TrimmedSizeEntity.builder().value(" qw").build(), 1),
            Arguments.of(TrimmedSizeEntity.builder().value(" qw ").build(), 1),
            Arguments.of(TrimmedSizeEntity.builder().value("qww").build(), 0),
            // max
            Arguments.of(TrimmedSizeEntity.builder().value("qwwww").build(), 0),
            Arguments.of(TrimmedSizeEntity.builder().value("qwwww ").build(), 0),
            Arguments.of(TrimmedSizeEntity.builder().value(" qwwww").build(), 0),
            Arguments.of(TrimmedSizeEntity.builder().value(" qwwww ").build(), 0),
            Arguments.of(TrimmedSizeEntity.builder().value("qwwwww").build(), 1)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(TrimmedSizeEntity input, int expectedSize) {
        
        assertThat(validator.validate(input).size())
            .withFailMessage("value: -" + input.getValue() + "-")
            .isGreaterThanOrEqualTo(expectedSize);
    }
}
