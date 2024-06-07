package com.andrascsanyi.encyclopediagalactica.common;

import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotEmtpy;
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
public class TrimmedNotEmptyEntity {
    
    @TrimmedNotEmtpy
    private String id;
}
