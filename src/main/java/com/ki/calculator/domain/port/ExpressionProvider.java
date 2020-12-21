package com.ki.calculator.domain.port;

import com.ki.calculator.domain.model.Expression;

import java.util.Optional;

public interface ExpressionProvider {

    Optional<Expression> find();
}
