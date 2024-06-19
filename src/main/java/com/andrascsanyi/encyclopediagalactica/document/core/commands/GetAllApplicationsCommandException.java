package com.andrascsanyi.encyclopediagalactica.document.core.commands;

public class GetAllApplicationsCommandException extends Exception {
    public GetAllApplicationsCommandException() {
    }
    
    public GetAllApplicationsCommandException(String message) {
        super(message);
    }
    
    public GetAllApplicationsCommandException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public GetAllApplicationsCommandException(Throwable cause) {
        super(cause);
    }
    
    public GetAllApplicationsCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
