package com.andrascsanyi.encyclopediagalactica.stepdefs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IHaveTheFollowingEntityEntity {
    private String entityType;
    private String id;
    private String name;
    private String description;
    private String outputDataKey;
}
