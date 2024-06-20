package com.andrascsanyi.encyclopediagalactica.common.trimmednotempty;

import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotEmpty;
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
    
    @TrimmedNotEmpty
    private String id;
}
