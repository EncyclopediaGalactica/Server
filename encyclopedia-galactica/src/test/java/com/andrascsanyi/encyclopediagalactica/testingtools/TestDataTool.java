package com.andrascsanyi.encyclopediagalactica.testingtools;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestDataTool {
    
    private Random random = new Random();
    private final static String characters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    
    public ApplicationEntity createApplicationEntity(boolean hasKey) {
        ApplicationEntity.ApplicationEntityBuilder builder = ApplicationEntity.builder();
        
        if (hasKey) {
            builder.id(random.nextLong(1000));
        }
        
        return builder
            .name(createRandomString(5))
            .description(createRandomString(5))
            .build();
    }
    
    public List<ApplicationEntity> createApplicationEntities(int amount, boolean hasKey) {
        List<ApplicationEntity> applicationEntities = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            applicationEntities.add(createApplicationEntity(hasKey));
        }
        return applicationEntities;
    }
    
    public String createRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }
}
