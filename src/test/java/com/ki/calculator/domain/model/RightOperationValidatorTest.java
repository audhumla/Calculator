package com.ki.calculator.domain.model;

import com.ki.calculator.domain.model.common.ValidationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ki.calculator.domain.model.MathOperation.DIVISION;
import static com.ki.calculator.domain.model.RightOperation.divideBy;
import static com.ki.calculator.domain.model.RightOperation.multiplyBy;
import static com.ki.calculator.domain.model.RightOperation.subtract;
import static com.ki.calculator.domain.model.RightOperation.sum;
import static org.assertj.core.api.Assertions.assertThat;

public class RightOperationValidatorTest {

    private final RightOperationValidator sut = new RightOperationValidator();

    @Nested
    class RightOperationIsCorrect {

        @ParameterizedTest
        @MethodSource("input")
        void shouldReturnTrueIfItsNotDivisionByZero(RightOperation rightOperation) {
            var actual = sut.validate(rightOperation);

            assertThat(actual.right()).isEqualTo("Right operation is validated");
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(sum(Decimal.of(5))),
                    Arguments.of(subtract(Decimal.of(0))),
                    Arguments.of(multiplyBy(Decimal.of(10))),
                    Arguments.of(divideBy(Decimal.of(1)))
            );
        }
    }

    @Nested
    class RightOperationIsNotCorrect {

        @Test
        void shouldReturnFalseIfItsNotDivisionByZero() {
            var actual = sut.validate(new RightOperation(DIVISION, Decimal.of(0)));

            assertThat(actual.left()).isInstanceOf(ValidationException.class);
            assertThat(actual.left().getMessage()).isEqualTo("Cannot divide a number by zero");
        }
    }
}