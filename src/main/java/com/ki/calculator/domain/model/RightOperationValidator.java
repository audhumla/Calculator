package com.ki.calculator.domain.model;

import com.ki.calculator.domain.model.common.Validator;

import java.util.function.Predicate;

public final class RightOperationValidator implements Validator<RightOperation> {

    private static final Predicate<RightOperation> isNumberZero =
            rightOperation -> rightOperation.getRightOperand().isZero();
    private static final Predicate<RightOperation> isDivision =
            rightOperation -> rightOperation.getOperation() == MathOperation.DIVISION;
    private static final Predicate<RightOperation> isDivisionByZero =
            rightOperation -> isDivision.test(rightOperation) && isNumberZero.test(rightOperation);

    @Override
    public boolean test(RightOperation rightOperation) {
        return !isDivisionByZero.test(rightOperation);
    }
}
