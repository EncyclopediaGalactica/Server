package com.andrascsanyi.encyclopediagalactica.document.api.graphql.output;

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
public class DocumentErrorOutput implements ApplicationResponse {
    private String message;
    private String errorDetails;
}
