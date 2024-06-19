package com.andrascsanyi.encyclopediagalactica.document.core.scenarios;

public class AddApplicationScenarioException extends RuntimeException {
    public AddApplicationScenarioException() {
    }
    
    public AddApplicationScenarioException(String message) {
        super(message);
    }
    
    public AddApplicationScenarioException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AddApplicationScenarioException(Throwable cause) {
        super(cause);
    }
    
    public AddApplicationScenarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
