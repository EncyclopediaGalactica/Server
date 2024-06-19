package com.andrascsanyi.encyclopediagalactica.document.core.scenarios;

public class GetAllApplicationsScenarioException extends RuntimeException {
    public GetAllApplicationsScenarioException() {
    }
    
    public GetAllApplicationsScenarioException(String message) {
        super(message);
    }
    
    public GetAllApplicationsScenarioException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public GetAllApplicationsScenarioException(Throwable cause) {
        super(cause);
    }
    
    public GetAllApplicationsScenarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
