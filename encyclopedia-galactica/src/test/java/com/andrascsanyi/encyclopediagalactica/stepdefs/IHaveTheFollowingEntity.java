package com.andrascsanyi.encyclopediagalactica.stepdefs;

import com.andrascsanyi.encyclopediagalactica.testingtools.ScenarioContext;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class IHaveTheFollowingEntity {
    
    @Autowired
    private ScenarioContext scenarioContext;
    
    @DataTableType
    public IHaveTheFollowingEntityEntity convert(Map<String, String> entry) {
        return IHaveTheFollowingEntityEntity.builder()
            .id(entry.get("EntityType"))
            .id(entry.get("Id"))
            .id(entry.get("Name"))
            .id(entry.get("Description"))
            .id(entry.get("OutputDataKey"))
            .build();
    }
    
    @Given("I have the following entity")
    public void iHaveTheFollowingEntity(@Transpose IHaveTheFollowingEntityEntity entity) {
    
    }
}
