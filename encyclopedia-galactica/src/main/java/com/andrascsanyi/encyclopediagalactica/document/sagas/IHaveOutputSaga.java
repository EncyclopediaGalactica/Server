package com.andrascsanyi.encyclopediagalactica.document.sagas;

public interface IHaveOutputSaga<T> {
    T execute();
}
