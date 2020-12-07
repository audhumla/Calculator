package com.ki.calculator.domain.model;

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
        return operation.compute(leftOperand, rightOperand);
    }
}