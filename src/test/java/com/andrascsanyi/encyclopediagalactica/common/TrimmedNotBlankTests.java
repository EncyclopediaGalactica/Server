package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import jakarta.validation.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TrimmedNotBlankTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private Validator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(TrimmedNotBlankEntity.builder().id("").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id("  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id("   ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" \t  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" \n  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" \r  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" \f  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" \s  ").build(), 1),
            Arguments.of(TrimmedNotBlankEntity.builder().id("a").build(), 0),
            Arguments.of(TrimmedNotBlankEntity.builder().id("a ").build(), 0),
            Arguments.of(TrimmedNotBlankEntity.builder().id("a  ").build(), 0),
            Arguments.of(TrimmedNotBlankEntity.builder().id(" a  ").build(), 0),
            Arguments.of(TrimmedNotBlankEntity.builder().id("  a  ").build(), 0)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(TrimmedNotBlankEntity input, int expected) {
        
        assertThat(validator.validate(input).size())
            .withFailMessage("string value: -" + input.getId() + "-")
            .isGreaterThanOrEqualTo(expected);
    }
}
