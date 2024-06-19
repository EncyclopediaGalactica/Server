package com.andrascsanyi.encyclopediagalactica.common.longvaluemustbeequalorgreaterto;

import com.andrascsanyi.encyclopediagalactica.common.validation.LongValueMustBeGreaterOrEqualTo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongValueMustBeEqualOrGreaterToCustomGroupEntity {
    
    @LongValueMustBeGreaterOrEqualTo(mustBeGreaterOrEqualTo = 1,
        groups = {LongValueMustBeEqualOrGreaterToCustomGroup.class})
    private Long value;
}
