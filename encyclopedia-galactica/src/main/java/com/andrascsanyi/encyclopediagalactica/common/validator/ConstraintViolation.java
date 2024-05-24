package com.andrascsanyi.encyclopediagalactica.common.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstraintViolation {
    private String rule;
    private String violationMessage;
}
