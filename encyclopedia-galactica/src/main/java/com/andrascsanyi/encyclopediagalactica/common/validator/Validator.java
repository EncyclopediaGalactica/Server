package com.andrascsanyi.encyclopediagalactica.common.validator;

public interface Validator<T> {
    boolean validate(final T input);
    String getViolationsForException();
    
}
