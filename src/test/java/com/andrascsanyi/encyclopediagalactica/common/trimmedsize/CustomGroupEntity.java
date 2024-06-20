package com.andrascsanyi.encyclopediagalactica.common.trimmedsize;

import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedSize;
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
public class CustomGroupEntity {
    
    @TrimmedSize(min = 3, max = 5, groups = {CustomGroup.class})
    private String value;
}
