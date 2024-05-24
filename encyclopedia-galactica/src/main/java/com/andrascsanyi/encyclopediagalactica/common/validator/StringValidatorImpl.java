package com.andrascsanyi.encyclopediagalactica.common.validator;

import org.springframework.stereotype.Component;

@Component
public class StringValidatorImpl implements StringValidator {
    @Override
    public boolean isNull(String s) {
        return s == null;
    }
    
    @Override
    public boolean isZero(String s) {
        return Long.parseLong(s) == 0;
    }
    
    @Override
    public boolean isTrimmedLengthGreaterOrEqualTo(String s, int length) {
        return s.trim().length() >= length;
    }
    
    @Override
    public boolean isEmpty(String s) {
        return s.isEmpty();
    }
    
    @Override
    public boolean isWhitespace(String s) {
        return s.trim().isEmpty();
    }
}
