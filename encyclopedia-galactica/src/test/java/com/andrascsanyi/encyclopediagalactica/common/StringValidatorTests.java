package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.common.validator.StringValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StringValidatorTests {
    
    @Autowired
    private StringValidator stringValidator;
    
    public static Stream<Arguments> isNullTestsData() {
        return Stream.of(
            Arguments.of(null, true),
            Arguments.of("", false),
            Arguments.of("a", false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("isNullTestsData")
    public void isNullTests(String s, boolean result) {
        assertThat(stringValidator.isNull(s)).isEqualTo(result);
    }
    
    public static Stream<Arguments> isEmptyTestData() {
        return Stream.of(
            Arguments.of("", true),
            Arguments.of(" ", false),
            Arguments.of("  ", false),
            Arguments.of("  c", false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("isEmptyTestData")
    public void isEmptyTests(String s, boolean result) {
        assertThat(stringValidator.isEmpty(s)).isEqualTo(result);
    }
    
    public static Stream<Arguments> isWhitespaceTestData() {
        return Stream.of(
            Arguments.of("", true),
            Arguments.of(" ", true),
            Arguments.of("  ", true),
            Arguments.of("  c", false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("isWhitespaceTestData")
    public void isWhitespaceTests(String s, boolean result) {
        assertThat(stringValidator.isWhitespace(s)).isEqualTo(result);
    }
    
}
