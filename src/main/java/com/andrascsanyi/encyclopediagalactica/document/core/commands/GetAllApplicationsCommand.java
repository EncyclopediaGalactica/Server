package com.andrascsanyi.encyclopediagalactica.document.core.commands;

import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import com.andrascsanyi.encyclopediagalactica.document.core.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllApplicationsCommand {
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    public List<ApplicationEntity> getAllApplications() throws GetAllApplicationsCommandException {
        try {
            List<ApplicationEntity> result = new ArrayList<>();
            applicationRepository.findAll().forEach(result::add);
            return result;
        } catch (Exception e) {
            throw new GetAllApplicationsCommandException(
                "Error happened while executing " + GetAllApplicationsCommand.class.getName(),
                e
            );
        }
    }
}
