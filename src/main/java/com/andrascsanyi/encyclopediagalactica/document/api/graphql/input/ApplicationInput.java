package com.andrascsanyi.encyclopediagalactica.document.api.graphql.input;

import com.andrascsanyi.encyclopediagalactica.common.validation.LongAsStringMustBeZero;
import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotBlank;
import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedNotEmtpy;
import com.andrascsanyi.encyclopediagalactica.common.validation.TrimmedSize;
import com.andrascsanyi.encyclopediagalactica.document.validation.AddApplicationScenario;
import jakarta.validation.constraints.NotNull;
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
public class ApplicationInput {
    
    @LongAsStringMustBeZero(groups = {AddApplicationScenario.class})
    private String id;
    
    @NotNull(groups = {AddApplicationScenario.class})
    @TrimmedNotEmtpy(groups = {AddApplicationScenario.class})
    @TrimmedNotBlank(groups = {AddApplicationScenario.class})
    @TrimmedSize(min = 3, max = 255, groups = {AddApplicationScenario.class})
    private String name;
    
    @NotNull(groups = {AddApplicationScenario.class})
    @TrimmedNotEmtpy(groups = {AddApplicationScenario.class})
    @TrimmedNotBlank(groups = {AddApplicationScenario.class})
    @TrimmedSize(min = 3, max = 255, groups = {AddApplicationScenario.class})
    private String description;
}
