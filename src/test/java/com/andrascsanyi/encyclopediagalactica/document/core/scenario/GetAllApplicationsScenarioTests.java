package com.andrascsanyi.encyclopediagalactica.document.core.scenario;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
import com.andrascsanyi.encyclopediagalactica.document.core.scenarios.AddApplicationScenario;
import com.andrascsanyi.encyclopediagalactica.document.core.scenarios.GetAllApplicationsScenario;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetAllApplicationsScenarioTests extends EncyclopediaGalacticaApplicationBaseTest {
    
    @Autowired
    private GetAllApplicationsScenario getAllApplicationsScenario;
    
    @Autowired
    private AddApplicationScenario addApplicationScenario;
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenTheDatabaseIsEmpty() {
        List<Application> applicationEntities = getAllApplicationsScenario.execute();
        assertThat(applicationEntities).isEmpty();
    }
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenTheDatabaseHasASingleElement() {
        Application input = Application.builder()
            .id(0L)
            .name("name")
            .description("description").build();
        
        Application result = addApplicationScenario.execute(input);
        
        List<Application> applicationEntities = getAllApplicationsScenario.execute();
        assertThat(applicationEntities).hasSize(1);
        assertThat(applicationEntities.get(0).getId()).isGreaterThanOrEqualTo(1);
        assertThat(applicationEntities.get(0).getName()).isEqualTo(input.getName());
        assertThat(applicationEntities.get(0).getDescription()).isEqualTo(input.getDescription());
    }
    
    @Tag("document")
    @Tag("core")
    @Test
    public void testWhenTheDatabaseHasMultipleElements() {
        for (int i = 0; i < 2; i++) {
            Application input = Application.builder()
                .id(0L)
                .name("name" + i)
                .description("description" + i).build();
            
            Application result = addApplicationScenario.execute(input);
        }
        List<Application> applicationEntities = getAllApplicationsScenario.execute();
        assertThat(applicationEntities).hasSize(2);
    }
}
