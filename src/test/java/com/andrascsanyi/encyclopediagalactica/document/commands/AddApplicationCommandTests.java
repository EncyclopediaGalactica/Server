package com.andrascsanyi.encyclopediagalactica.document.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.testdata.AddApplicationScenarioInputValidationData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationCommandTests extends EncyclopediaGalacticaApplicationBaseTest {

    @Autowired private AddApplicationCommand addApplicationCommand;

    @Autowired private GetAllApplicationsCommand getAllApplicationsCommand;

    @ParameterizedTest
    @ArgumentsSource(AddApplicationScenarioInputValidationData.class)
    public void inputValidation(ApplicationInput input) {
        assertThatThrownBy(
                        () -> {
                            addApplicationCommand.addApplication(input);
                        })
                .isInstanceOf(AddApplicationCommandException.class);
    }

    @Test
    public void createApplication()
            throws GetAllApplicationsCommandException, AddApplicationCommandException {
        ApplicationInput input =
                ApplicationInput.builder().id("0").name("name").description("desc").build();
        addApplicationCommand.addApplication(input);
        List<ApplicationOutput> result =
                getAllApplicationsCommand.getAllApplications().stream().toList();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isNotEmpty().isNotBlank().isNotEqualTo("0");
        assertThat(result.get(0).getName()).isEqualTo(input.getName());
        assertThat(result.get(0).getDescription()).isEqualTo(input.getDescription());
    }
}
