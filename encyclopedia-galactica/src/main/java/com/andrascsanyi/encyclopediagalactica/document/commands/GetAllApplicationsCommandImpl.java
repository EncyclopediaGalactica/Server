package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllApplicationsCommandImpl implements GetAllApplicationsCommand {
    
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    
    public GetAllApplicationsCommandImpl(ApplicationMapper applicationMapper,
                                         ApplicationRepository applicationRepository) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
    }
    
    @Override
    public List<ApplicationOutput> getAllApplications() throws GetAllApplicationsCommandException {
        try {
            List<ApplicationEntity> result = new ArrayList<>();
            applicationRepository.findAll().forEach(result::add);
            return applicationMapper.toApplicationOutputList(result);
        } catch (Exception e) {
            throw new GetAllApplicationsCommandException(
                "Error happened while executing " + GetAllApplicationsCommand.class.getName(),
                e
            );
        }
    }
}
