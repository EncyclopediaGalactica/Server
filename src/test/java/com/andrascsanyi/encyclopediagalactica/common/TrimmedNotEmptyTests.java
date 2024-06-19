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
public class TrimmedNotEmptyTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(TrimmedNotEmptyEntity.builder().id("").build(), 1),
            Arguments.of(TrimmedNotEmptyEntity.builder().id(" ").build(), 1),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("  ").build(), 1),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("   ").build(), 1),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("a").build(), 0),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("a ").build(), 0),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("a  ").build(), 0),
            Arguments.of(TrimmedNotEmptyEntity.builder().id(" a  ").build(), 0),
            Arguments.of(TrimmedNotEmptyEntity.builder().id("  a  ").build(), 0)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(TrimmedNotEmptyEntity input, int expected) {
        
        assertThat(validator.validate(input).size())
            .withFailMessage("string value: -" + input.getId() + "-")
            .isGreaterThanOrEqualTo(expected);
    }
}
