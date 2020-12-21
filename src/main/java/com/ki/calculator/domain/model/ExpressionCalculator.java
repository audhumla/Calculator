package com.ki.calculator.domain.model;

import com.ki.calculator.domain.model.common.Either;
import com.ki.calculator.domain.model.common.Validator;

public class ExpressionCalculator {

    private final Validator<RightOperation> validator = new RightOperationValidator();

    public Either<Exception, Decimal> compute(Expression expression) {
        var result = expression.getFirstLeftOperand();
        for (RightOperation rightOperation : expression.getRightOperations()) {
            var validationResult = validator.validate(rightOperation);
            if (validationResult.isLeft())
                return Either.left(validationResult.left());
            result = performComputation(result, rightOperation);
        }
        return Either.right(result);
    }

    private Decimal performComputation(Decimal leftOperand, RightOperation rightOperation) {
        MathOperation operation = rightOperation.getOperation();
        Decimal rightOperand = rightOperation.getRightOperand();
        return operation.compute(leftOperand, rightOperand);
    }
}
