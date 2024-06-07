package com.andrascsanyi.encyclopediagalactica.document.api.graphql.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ApplicationOutput implements ApplicationResponse {
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String id;
    
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
}
