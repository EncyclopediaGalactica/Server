package com.andrascsanyi.encyclopediagalactica.document.sagas;

public class AddApplicationSagaException extends RuntimeException {
    public AddApplicationSagaException() {
    }
    
    public AddApplicationSagaException(String message) {
        super(message);
    }
    
    public AddApplicationSagaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AddApplicationSagaException(Throwable cause) {
        super(cause);
    }
    
    public AddApplicationSagaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
