package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.ApplicationResponse;
import com.andrascsanyi.encyclopediagalactica.document.api.graphql.DocumentErrorOutput;
import com.andrascsanyi.encyclopediagalactica.document.commands.AddApplicationCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddApplicationSaga {

    @Autowired private AddApplicationCommand addApplicationCommand;

    private static final Logger log = LoggerFactory.getLogger(AddApplicationSaga.class);

    public ApplicationResponse execute(ApplicationInput input) {
        try {
            return addApplicationCommand.addApplication(input);
        } catch (Exception e) {

            StringBuilder builder = new StringBuilder();
            builder.append("Error happened while executing ")
                    .append(AddApplicationSaga.class.getSimpleName());

            return DocumentErrorOutput.builder()
                    .setMessage(builder.toString())
                    .setErrorDetails(e.toString())
                    .build();
        }
    }
}
