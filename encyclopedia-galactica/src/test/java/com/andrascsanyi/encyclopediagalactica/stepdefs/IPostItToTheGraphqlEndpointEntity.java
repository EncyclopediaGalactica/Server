package com.andrascsanyi.encyclopediagalactica.stepdefs;

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
public class IPostItToTheGraphqlEndpointEntity {
    private String inputDataKey;
    private String methodName;
    private String outputDataKey;
}
