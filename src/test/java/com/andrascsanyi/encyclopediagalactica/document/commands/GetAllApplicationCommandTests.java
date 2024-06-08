package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.EncyclopediaGalacticaApplicationBaseTest;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
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
    
    @Test
    public void testWhenTheDatabaseIsEmpty() throws GetAllApplicationsCommandException {
        List<ApplicationOutput> result = getAllApplicationsCommand.getAllApplications();
        
        assertThat(result).isEmpty();
    }
    
    @Test
    public void testWhenOnlyASingleItemInTheDatabase() throws AddApplicationCommandException, GetAllApplicationsCommandException {
        ApplicationInput input = ApplicationInput.builder()
            .name("name")
            .description("description")
            .build();
        addApplicationCommand.addApplication(input);
        
        List<ApplicationOutput> resultList = getAllApplicationsCommand.getAllApplications();
        
        assertThat(resultList.size()).isEqualTo(1);
        assertThat(resultList.get(0).getId()).isGreaterThanOrEqualTo("1").isNotEqualTo("0");
        assertThat(resultList.get(0).getName()).isEqualTo(input.getName());
        assertThat(resultList.get(0).getDescription()).isEqualTo(input.getDescription());
    }
    
    @Test
    public void testWhenMultipleItemsInTheDatabase() throws AddApplicationCommandException, GetAllApplicationsCommandException {
        for (int i = 0; i < 2; i++) {
            ApplicationInput input = ApplicationInput.builder()
                .name("name" + i)
                .description("description" + i)
                .build();
            addApplicationCommand.addApplication(input);
        }
        
        List<ApplicationOutput> resultList = getAllApplicationsCommand.getAllApplications();
        
        assertThat(resultList.size()).isEqualTo(2);
    }
}
