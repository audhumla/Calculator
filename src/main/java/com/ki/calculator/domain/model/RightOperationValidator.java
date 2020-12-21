package com.ki.calculator.domain.model;

import com.ki.calculator.domain.model.common.Either;
import com.ki.calculator.domain.model.common.ValidationException;
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
    public Either<ValidationException, String> validate(RightOperation rightOperation) {
        if (isDivisionByZero.test(rightOperation)) {
            return Either.left(new ValidationException("Cannot divide a number by zero"));
        }
        return Either.right("Right operation is validated");
    }
}
