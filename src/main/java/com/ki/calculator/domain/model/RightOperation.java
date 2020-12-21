package com.ki.calculator.domain.model;

import java.util.Objects;

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

    public static RightOperation sum(Decimal rightOperand) {
        return new RightOperation(MathOperation.SUM, rightOperand);
    }

    public static RightOperation divideBy(Decimal rightOperand) {
        return new RightOperation(MathOperation.DIVISION, rightOperand);
    }

    public static RightOperation multiplyBy(Decimal rightOperand) {
        return new RightOperation(MathOperation.MULTIPLICATION, rightOperand);
    }

    public static RightOperation subtract(Decimal rightOperand) {
        return new RightOperation(MathOperation.DIFFERENCE, rightOperand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RightOperation that = (RightOperation) o;
        return operation == that.operation && Objects.equals(rightOperand, that.rightOperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, rightOperand);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("RightOperation{")
                .append("operation=")
                .append(operation)
                .append(", rightOperand=")
                .append(rightOperand)
                .append('}')
                .toString();
    }
}