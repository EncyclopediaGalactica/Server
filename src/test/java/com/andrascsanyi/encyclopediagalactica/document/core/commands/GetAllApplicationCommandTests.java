package com.andrascsanyi.encyclopediagalactica.document.core.commands;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetAllApplicationCommandTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private GetAllApplicationsCommand getAllApplicationsCommand;
    
    @Autowired
    private AddApplicationCommand addApplicationCommand;
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenTheDatabaseIsEmpty() throws GetAllApplicationsCommandException {
        List<ApplicationEntity> result = getAllApplicationsCommand.getAllApplications();
        
        assertThat(result).isEmpty();
    }
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenOnlyASingleItemInTheDatabase() throws AddApplicationCommandException, GetAllApplicationsCommandException {
        ApplicationEntity input = ApplicationEntity.builder()
            .id(0L)
            .name("name")
            .description("description")
            .build();
        addApplicationCommand.addApplication(input);
        
        List<ApplicationEntity> resultList = getAllApplicationsCommand.getAllApplications();
        
        assertThat(resultList.size()).isEqualTo(1);
        assertThat(resultList.get(0).getId()).isGreaterThanOrEqualTo(1L).isNotEqualTo(0);
        assertThat(resultList.get(0).getName()).isEqualTo(input.getName());
        assertThat(resultList.get(0).getDescription()).isEqualTo(input.getDescription());
    }
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenMultipleItemsInTheDatabase() throws AddApplicationCommandException, GetAllApplicationsCommandException {
        for (int i = 0; i < 2; i++) {
            ApplicationEntity input = ApplicationEntity.builder()
                .id(0L)
                .name("name" + i)
                .description("description" + i)
                .build();
            addApplicationCommand.addApplication(input);
        }
        
        List<ApplicationEntity> resultList = getAllApplicationsCommand.getAllApplications();
        
        assertThat(resultList.size()).isEqualTo(2);
    }
}
