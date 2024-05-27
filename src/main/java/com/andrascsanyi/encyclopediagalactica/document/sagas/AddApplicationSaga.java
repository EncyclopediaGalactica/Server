package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.commands.AddApplicationCommand;
import com.andrascsanyi.encyclopediagalactica.document.graphql.input.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.output.ApplicationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddApplicationSaga {

    @Autowired
    private AddApplicationCommand addApplicationCommand;

    public ApplicationOutput execute(ApplicationInput input) {
        try {
            return addApplicationCommand.addApplication(input);
        } catch (Exception e) {
            throw new AddApplicationSagaException(
                    "Error happened while executing " + AddApplicationSaga.class.getName(), e);
        }
    }
}
