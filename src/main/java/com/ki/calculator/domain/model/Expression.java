package com.ki.calculator.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expression {
    private final Decimal firstLeftOperand;
    private final List<RightOperation> rightOperations;

    public Expression(Decimal firstLeftOperand, List<RightOperation> rightOperations) {
        this.firstLeftOperand = firstLeftOperand;
        this.rightOperations = new ArrayList<>(rightOperations);

        Objects.requireNonNull(firstLeftOperand, "firstLeftOperand cannot be null");
        Objects.requireNonNull(rightOperations, "rightOperations cannot be null");
    }

    public Decimal compute() {
        var newValue = firstLeftOperand;
        for (RightOperation rightOperation : rightOperations) {
            MathOperation operation = rightOperation.getOperation();
            Decimal rightOperand = rightOperation.getRightOperand();
            newValue = operation.compute(newValue, rightOperand);
        }
        return newValue;
    }
}
