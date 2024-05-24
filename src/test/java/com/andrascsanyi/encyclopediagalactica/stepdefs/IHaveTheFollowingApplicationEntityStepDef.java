package com.andrascsanyi.encyclopediagalactica.stepdefs;

import com.andrascsanyi.encyclopediagalactica.testingtools.ScenarioContext;
import com.andrascsanyi.encyclopediagalactica.testingtools.TestDataTool;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class IHaveTheFollowingApplicationEntityStepDef {
    
    @Autowired
    private ScenarioContext scenarioContext;
    
    @Autowired
    private TestDataTool testDataTool;
    
    @DataTableType
    public IHaveTheFollowingApplicationEntity convert(Map<String, String> row) {
        return IHaveTheFollowingApplicationEntity.builder()
            .hasKey(row.get("HasKey"))
            .outputDataKey(row.get("OutputDataKey"))
            .build();
    }
    
    @Given("I have the following Application entitiy")
    public void IHaveTheFollowingApplicationEntity(@Transpose IHaveTheFollowingApplicationEntity data) {
        
        boolean hasKey = determineBoolValue(data.getHasKey());
        
        scenarioContext.add(
            data.getOutputDataKey(),
            testDataTool.createApplicationEntity(hasKey));
    }
    
    private boolean determineBoolValue(String boolValue) {
        return boolValue != null && boolValue.equals("No");
    }
}
