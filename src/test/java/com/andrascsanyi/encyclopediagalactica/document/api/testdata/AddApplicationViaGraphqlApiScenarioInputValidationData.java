package com.andrascsanyi.encyclopediagalactica.document.api.testdata;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.validation.AddApplicationScenario;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Test data for testing input validation of {@link AddApplicationScenario}.
 * <p>
 * The provided test data includes only invalid input parameters.
 */
public class AddApplicationViaGraphqlApiScenarioInputValidationData implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(ApplicationInput.builder().setId("1").setName("name").setDescription("desc").build()),
            
            Arguments.of(ApplicationInput.builder().setId("0").setName(null).setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName(" ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("  ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("   ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("ab").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("ab ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName(" ab ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName(" ab ").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a\r").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("aa\r").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a\t").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("aa\t").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a\f").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("aa\f").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("a\s").setDescription("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setName("aa\s").setDescription("desc").build()),
            
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription(" ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("  ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("   ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("a").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("ab").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("ab ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription(" ab ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription(" ab ").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("a\r").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("aa\r").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("a\t").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("aa\t").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("a\f").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("aa\f").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("a\s").setName("desc").build()),
            Arguments.of(ApplicationInput.builder().setId("0").setDescription("aa\s").setName("desc").build()));
    }
}
