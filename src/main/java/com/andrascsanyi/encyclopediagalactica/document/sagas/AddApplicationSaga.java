package com.andrascsanyi.encyclopediagalactica.document.sagas;

import com.andrascsanyi.encyclopediagalactica.document.commands.AddApplicationCommand;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationInput;
import com.andrascsanyi.encyclopediagalactica.document.graphql.ApplicationOutput;
import org.springframework.stereotype.Service;

@Service
public class AddApplicationSaga implements IHaveInputOutputSaga<ApplicationInput, ApplicationOutput> {
    
    private final AddApplicationCommand addApplicationCommand;
    
    public AddApplicationSaga(AddApplicationCommand addApplicationCommand) {
        
        this.addApplicationCommand = addApplicationCommand;
    }
    
    @Override
    public ApplicationOutput execute(ApplicationInput input) {
        try {
            return addApplicationCommand.addApplication(input);
        } catch (Exception e) {
            throw new AddApplicationSagaException(
                "Error happened while executing " + AddApplicationSaga.class.getName(),
                e
            );
        }
    }
}
