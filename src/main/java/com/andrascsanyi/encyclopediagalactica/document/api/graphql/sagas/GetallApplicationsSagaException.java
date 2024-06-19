package com.andrascsanyi.encyclopediagalactica.document.api.graphql.sagas;

public class GetallApplicationsSagaException extends RuntimeException {
    public GetallApplicationsSagaException() {
    }
    
    public GetallApplicationsSagaException(String message) {
        super(message);
    }
    
    public GetallApplicationsSagaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public GetallApplicationsSagaException(Throwable cause) {
        super(cause);
    }
    
    public GetallApplicationsSagaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
