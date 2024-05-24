package com.andrascsanyi.encyclopediagalactica.common.validator;

import org.springframework.stereotype.Component;

@Component
public class LongValidatorImpl implements LongValidator {
    @Override
    public boolean greaterThan(Long l, int i) {
        return l > i;
    }
}
