package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;

import java.util.List;

public interface GetAllApplicationsCommand {
    List<ApplicationOutput> getAllApplications() throws GetAllApplicationsCommandException;
}
