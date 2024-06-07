package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.common.validation.LongAsStringMustBeGreaterOrEqualToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LongAsStringMustBeGreaterOrEqualToOneEntity {
    
    @LongAsStringMustBeGreaterOrEqualToOne
    private String id;
}
