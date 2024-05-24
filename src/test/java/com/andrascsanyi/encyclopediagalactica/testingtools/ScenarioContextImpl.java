package com.andrascsanyi.encyclopediagalactica.testingtools;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class ScenarioContextImpl implements ScenarioContext {
    
    private Map<String, Object> context = new HashMap<>();
    
    @Override
    public Object get(String key) {
        return context.get(key);
    }
    
    @Override
    public void add(String key, Object value) {
        context.put(key, value);
    }
    
}
