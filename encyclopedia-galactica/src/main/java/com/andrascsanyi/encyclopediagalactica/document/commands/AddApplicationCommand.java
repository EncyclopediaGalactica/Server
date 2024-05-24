package com.andrascsanyi.encyclopediagalactica.document.commands;

import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;

public interface AddApplicationCommand {
    ApplicationOutput addApplication(ApplicationInput applicationInput) throws AddApplicationCommandException;
}
