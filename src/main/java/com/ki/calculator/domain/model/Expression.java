package com.ki.calculator.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class Expression {
    private final Decimal firstLeftOperand;
    private final List<RightOperation> rightOperations = new ArrayList<>();

    public Expression(long firstLeftOperand) {
        this.firstLeftOperand = Decimal.of(firstLeftOperand);
    }

    public Expression(Decimal firstLeftOperand) {
        this.firstLeftOperand = firstLeftOperand;
    }

    public Expression append(RightOperation rightOperation) {
        Objects.requireNonNull(rightOperation, "Cannot add a null rightOperation");
        rightOperations.add(rightOperation);
        return this;
    }

    public Expression append(Collection<RightOperation> rightOperation) {
        Objects.requireNonNull(rightOperation, "Cannot add a null rightOperation");
        rightOperations.addAll(rightOperation);
        return this;
    }

    public Decimal getFirstLeftOperand() {
        return Decimal.copy(firstLeftOperand);
    }

    public List<RightOperation> getRightOperations() {
        return new ArrayList<>(rightOperations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(firstLeftOperand, that.firstLeftOperand) && Objects.equals(rightOperations, that.rightOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstLeftOperand, rightOperations);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Expression{")
                .append("firstLeftOperand=")
                .append(firstLeftOperand)
                .append(", rightOperations=")
                .append(rightOperations)
                .append('}')
                .toString();
    }
}
