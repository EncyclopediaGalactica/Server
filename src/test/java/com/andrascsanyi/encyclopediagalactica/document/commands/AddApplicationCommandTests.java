package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddApplicationCommandTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private AddApplicationCommand addApplicationCommand;
    
    @Autowired
    private GetAllApplicationsCommand getAllApplicationsCommand;
    
    @Test
    public void createApplication()
        throws GetAllApplicationsCommandException, AddApplicationCommandException {
        ApplicationEntity input = ApplicationEntity.builder()
            .id(0L)
            .name("name")
            .description("desc")
            .build();
        addApplicationCommand.addApplication(input);
        List<ApplicationEntity> result = getAllApplicationsCommand.getAllApplications().stream().toList();
        
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isNotEqualTo(0L);
        assertThat(result.get(0).getName()).isEqualTo(input.getName());
        assertThat(result.get(0).getDescription()).isEqualTo(input.getDescription());
    }
}
