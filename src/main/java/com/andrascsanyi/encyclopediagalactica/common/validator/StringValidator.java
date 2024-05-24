package com.andrascsanyi.encyclopediagalactica.common.validator;

public interface StringValidator {
    boolean isNull(String id);
    boolean isZero(String id);
    boolean isTrimmedLengthGreaterOrEqualTo(String input, int i);
    boolean isEmpty(String id);
    boolean isWhitespace(String id);
}
