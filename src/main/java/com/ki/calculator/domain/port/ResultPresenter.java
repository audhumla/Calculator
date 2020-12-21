package com.ki.calculator.domain.port;

import com.ki.calculator.domain.model.common.Either;

public interface ResultPresenter<T> {

    void show(Either<Exception, T> result);
}
