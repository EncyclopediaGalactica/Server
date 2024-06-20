package com.andrascsanyi.encyclopediagalactica.common.trimmednotblank;

import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotBlank;
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
public class DefaultGroupEntity {
    
    @TrimmedNotBlank
    private String id;
}
