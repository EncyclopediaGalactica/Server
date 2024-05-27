package com.andrascsanyi.encyclopediagalactica.document.validators;

import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddApplicationInputValidatorTests {
    
    @Autowired
    private AddApplicationSagaInputValidator validator;
    
    public static Stream<Arguments> testData() {
        return Stream.of(
            // Id
            Arguments.of(ApplicationOutput.builder().setId("1").setName("name").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("name").setDescription("desc").build(), true),
            Arguments.of(ApplicationOutput.builder().setId("").setName("name").setDescription("desc").build(), true),
            Arguments.of(ApplicationOutput.builder().setId(" ").setName("name").setDescription("desc").build(), true),
            Arguments.of(ApplicationOutput.builder().setId("  ").setName("name").setDescription("desc").build(), true),
            Arguments.of(ApplicationOutput.builder().setId(null).setName("name").setDescription("desc").build(), true),
            // name
            Arguments.of(ApplicationOutput.builder().setId("0").setName(null).setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("a").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("ab").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName(" ").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("  ").setDescription("desc").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("desc").build(), true),
            // desc
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription(null).build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("d").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("de").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription(" ").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("  ").build(), false),
            Arguments.of(ApplicationOutput.builder().setId("0").setName("abc").setDescription("desc").build(), true)
        );
    }
    
    @ParameterizedTest
    @MethodSource("testData")
    public void test(ApplicationOutput applicationInput, boolean expectedResult) {
        
        StringBuilder builder = new StringBuilder("ApplicationInput");
        builder
            .append("[id]: -").append(applicationInput.getId()).append("- ")
            .append("[name]: -").append(applicationInput.getName()).append("- ")
            .append("[description]: -").append(applicationInput.getDescription()).append("- ");
        
        boolean r = validator.validate(applicationInput);
        
        assertThat(validator.validate(applicationInput))
            .withFailMessage(builder.toString())
            .isEqualTo(expectedResult);
    }
}
