package com.ki.calculator.domain.model;

public class RightOperation {
    private final MathOperation operation;
    private final Decimal rightOperand;

    public RightOperation(MathOperation operation, Decimal rightOperand) {
        this.operation = operation;
        this.rightOperand = rightOperand;
    }

    public MathOperation getOperation() {
        return operation;
    }

    public Decimal getRightOperand() {
        return rightOperand;
    }
}
