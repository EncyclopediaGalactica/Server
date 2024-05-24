package com.andrascsanyi.encyclopediagalactica.document.commands;

public class AddApplicationCommandException extends Exception {
    public AddApplicationCommandException() {
    }
    
    public AddApplicationCommandException(String message) {
        super(message);
    }
    
    public AddApplicationCommandException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AddApplicationCommandException(Throwable cause) {
        super(cause);
    }
    
    public AddApplicationCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
