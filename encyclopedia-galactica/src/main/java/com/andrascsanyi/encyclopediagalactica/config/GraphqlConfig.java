package com.andrascsanyi.encyclopediagalactica.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {
    
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiring -> {
            wiring.scalar(ExtendedScalars.DateTime);
            wiring.scalar(ExtendedScalars.GraphQLLong);
        };
    }
}
