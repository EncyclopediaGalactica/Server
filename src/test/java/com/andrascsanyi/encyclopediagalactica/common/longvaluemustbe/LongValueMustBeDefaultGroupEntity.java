package com.andrascsanyi.encyclopediagalactica.common.longvaluemustbe;

import com.andrascsanyi.encyclopediagalactica.common.validation.LongValueMustBe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LongValueMustBeDefaultGroupEntity {
    
    @LongValueMustBe(mustBe = 0)
    private Long value;
}
