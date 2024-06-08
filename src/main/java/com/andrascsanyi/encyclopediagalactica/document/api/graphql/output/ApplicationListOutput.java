package com.andrascsanyi.encyclopediagalactica.document.api.graphql.output;

import java.util.List;

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
public class ApplicationListOutput implements  ApplicationResponse {
    
    private List<ApplicationOutput> applicationList; 
}
