package com.andrascsanyi.encyclopediagalactica;

import com.andrascsanyi.encyclopediagalactica.config.ValidationConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(
    classes = EncyclopediaGalacticaApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {
    ValidationConfig.class
})
@ActiveProfiles("test")
public class EncyclopediaGalacticaApplicationBaseTest {
}
