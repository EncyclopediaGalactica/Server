package com.andrascsanyi.encyclopediagalactica.document.mappers;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ApplicationMapper {
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationEntity toApplicationEntity(ApplicationInput applicationInput);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationOutput toApplicationOutput(ApplicationEntity applicationEntity);
    
    default List<ApplicationOutput> toApplicationOutputList(List<ApplicationEntity> result) {
        return result.stream().map(this::toApplicationOutput).toList();
    }
}
