package com.andrascsanyi.encyclopediagalactica.document.api.graphql.mappers;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationListOutputItem;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.entities.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.core.entities.Application;
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
    Application toApplicationEntity(ApplicationInput applicationInput);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationOutput toApplicationOutput(Application applicationEntity);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    ApplicationListOutputItem toApplicationListOutputItem(Application applicationEntity);
    
    default List<ApplicationListOutputItem> toApplicationListOutputItems(
        List<Application> applicationEntities
    ) {
        return applicationEntities.stream().map(this::toApplicationListOutputItem).toList();
    }
    
    default List<ApplicationOutput> toApplicationOutputList(List<Application> result) {
        return result.stream().map(this::toApplicationOutput).toList();
    }
}
