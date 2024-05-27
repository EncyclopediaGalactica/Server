package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import com.andrascsanyi.encyclopediagalactica.document.mappers.ApplicationMapper;
import com.andrascsanyi.encyclopediagalactica.document.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllApplicationsCommand {
   
    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private ApplicationRepository applicationRepository;
    
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
