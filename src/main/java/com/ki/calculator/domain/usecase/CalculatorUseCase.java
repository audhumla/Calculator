package com.ki.calculator.domain.usecase;

import com.ki.calculator.domain.model.Decimal;
import com.ki.calculator.domain.model.Expression;
import com.ki.calculator.domain.model.ExpressionCalculator;
import com.ki.calculator.domain.model.common.Either;
import com.ki.calculator.domain.port.ExpressionProvider;
import com.ki.calculator.domain.port.ResultPresenter;

import java.util.Optional;

public class CalculatorUseCase {

    private final ExpressionProvider expressionProvider;
    private final ExpressionCalculator calculator = new ExpressionCalculator();
    private final ResultPresenter<Decimal> presenter;

    public CalculatorUseCase(
            ExpressionProvider expressionProvider,
            ResultPresenter<Decimal> presenter
    ) {
        this.expressionProvider = expressionProvider;
        this.presenter = presenter;
    }

    public void execute() {
        Optional<Expression> expressionMaybe = expressionProvider.find();
        expressionMaybe.ifPresentOrElse(
                expression -> presenter.show(
                        calculator.compute(expression)
                ),
                () -> presenter.show(
                        Either.left(
                                new IllegalArgumentException("No Expression can be found")
                        )
                )
        );

    }
}
