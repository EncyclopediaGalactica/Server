package com.andrascsanyi.encyclopediagalactica.document.core.testdata;

import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AddApplicationEntityScenarioInputValidationTestData implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(Application.builder().id(1L).name("asd").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name(null).description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\t").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\r").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("\n").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("as").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("as ").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name(" as ").description("asd").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description(null).build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\t").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\r").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("\n").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description("as ").build()),
            Arguments.of(Application.builder().id(0L).name("asd").description(" as ").build())
        );
    }
}
