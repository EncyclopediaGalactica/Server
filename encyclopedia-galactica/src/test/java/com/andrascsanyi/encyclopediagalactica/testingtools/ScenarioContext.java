package com.andrascsanyi.encyclopediagalactica.testingtools;

public interface ScenarioContext {
    Object get(String key);
    void add(String key, Object value);
}
