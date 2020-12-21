package com.ki.calculator.domain.model;

import java.util.Objects;

@FunctionalInterface
interface BinaryOperation {
    Decimal compute(Decimal leftOperand, Decimal rightOperand);
}

public enum MathOperation implements BinaryOperation {

    SUM(Decimal::plus),
    DIFFERENCE(Decimal::minus),
    MULTIPLICATION(Decimal::times),
    DIVISION(Decimal::div),
    ;

    private final BinaryOperation operation;

    MathOperation(BinaryOperation operation) {
        this.operation = operation;
    }

    @Override
    public Decimal compute(Decimal leftOperand, Decimal rightOperand) {
        Objects.requireNonNull(leftOperand, "Cannot compute" + this + "with null left operand");
        Objects.requireNonNull(rightOperand, "Cannot compute" + this + "with null right operand");

        return operation.compute(leftOperand, rightOperand);
    }
}