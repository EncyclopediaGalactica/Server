package com.andrascsanyi.encyclopediagalactica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.andrascsanyi.encyclopediagalactica.config.GraphqlConfig;
import com.andrascsanyi.encyclopediagalactica.config.ValidationConfig;

@SpringBootTest
@ContextConfiguration(classes = {
    GraphqlConfig.class,
    ValidationConfig.class
})
class EncyclopediaGalacticaApplicationTests {
    
    @Test
    void contextLoads() {
    
    }
    
}
