package com.andrascsanyi.encyclopediagalactica.document.mappers;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationEntity toApplicationEntity(ApplicationOutput applicationInput);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationOutput toApplicationOutput(ApplicationEntity applicationEntity);
    
    default List<ApplicationOutput> toApplicationOutputList(List<ApplicationEntity> result) {
        return result.stream().map(this::toApplicationOutput).toList();
    }
}
