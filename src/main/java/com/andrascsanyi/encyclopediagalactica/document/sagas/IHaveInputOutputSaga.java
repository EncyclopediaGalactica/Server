package com.andrascsanyi.encyclopediagalactica.document.sagas;

public interface IHaveInputOutputSaga<InputType, OutputType> {
    OutputType execute(InputType input);
}
