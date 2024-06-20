package com.andrascsanyi.encyclopediagalactica.common.longasstringmustbezero;

import com.andrascsanyi.encyclopediagalactica.common.validation.LongAsStringMustBeZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultGroupEntity {
    
    @LongAsStringMustBeZero
    private String id;
}
