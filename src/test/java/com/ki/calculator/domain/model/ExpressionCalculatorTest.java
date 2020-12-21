package com.ki.calculator.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ki.calculator.domain.model.RightOperation.divideBy;
import static com.ki.calculator.domain.model.RightOperation.subtract;
import static com.ki.calculator.domain.model.RightOperation.sum;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionCalculatorTest {

    @Nested
    class ComputeTest {

        @ParameterizedTest
        @MethodSource("input")
        void computeShouldWork(Expression input, Decimal result) {
            var actual = new ExpressionCalculator().compute(input);

            assertThat(actual.right()).isEqualTo(result);
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    // 10 + 10 + 10 + 10 - 5 = 35
                    Arguments.of(
                            new Expression(10)
                                    .append(sum(Decimal.of(10)))
                                    .append(sum(Decimal.of(10)))
                                    .append(sum(Decimal.of(10)))
                                    .append(subtract(Decimal.of(5))),
                            Decimal.of(35)
                    ),
                    // (1 / 1000) - 5 = -4.999
                    Arguments.of(
                            new Expression(1)
                                    .append(divideBy(Decimal.of(10)))
                                    .append(divideBy(Decimal.of(10)))
                                    .append(divideBy(Decimal.of(10)))
                                    .append(subtract(Decimal.of(5))),
                            Decimal.of(-4.999)
                    )
            );
        }
    }

    @Nested
    class ComputeTestDivisionByZero {

        @Test
        void shouldReturnRight() {
            var input = new Expression(10)
                    .append(divideBy(Decimal.of(0)))
                    .append(sum(Decimal.of(10)));

            var actual = new ExpressionCalculator().compute(input);

            assertThat(actual.isLeft()).isTrue();
        }
    }
}