package com.ki.calculator.domain.model.common;

@FunctionalInterface
public interface Validator<T> {

    Either<ValidationException, String> validate(T obj);
}
