package com.andrascsanyi.encyclopediagalactica.common.longasstringmustbegreaterorequaltoone;

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
public class CustomGroupEntity {
    
    @LongAsStringMustBeGreaterOrEqualToOne(groups = {CustomGroup.class})
    private String id;
}
