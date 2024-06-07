package com.andrascsanyi.encyclopediagalactica.document.testdata;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.validation.AddApplicationScenario;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Test data for testing input validation of {@link AddApplicationScenario}.
 * <p>
 * The provided test data includes only invalid input parameters.
 */
public class AddApplicationScenarioInputValidationData implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(ApplicationInput.builder().id("1").name("name").description("desc").build()),
            
            Arguments.of(ApplicationInput.builder().id("0").name(null).description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name(" ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("  ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("   ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("a").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("ab").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("ab ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name(" ab ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name(" ab ").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("a\r").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("aa\r").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("a\t").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("aa\t").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("a\f").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("aa\f").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("a\s").description("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").name("aa\s").description("desc").build()),
            
            Arguments.of(ApplicationInput.builder().id("0").description("").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description(" ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("  ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("   ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("a").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("ab").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("ab ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description(" ab ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description(" ab ").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("a\r").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("aa\r").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("a\t").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("aa\t").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("a\f").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("aa\f").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("a\s").name("desc").build()),
            Arguments.of(ApplicationInput.builder().id("0").description("aa\s").name("desc").build())
        );
    }
}
