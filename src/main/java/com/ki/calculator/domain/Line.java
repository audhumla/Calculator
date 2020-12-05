package com.ki.calculator.domain;

public class Line {
    private final MathOperation operation;
    private final DomainInteger number;

    public Line(MathOperation operation, long rightValue) {
        this.operation = operation;
        this.number = DomainInteger.of(rightValue);
    }

    public MathOperation getOperation() {
        return operation;
    }

    public DomainInteger getNumber() {
        return number;
    }
}
