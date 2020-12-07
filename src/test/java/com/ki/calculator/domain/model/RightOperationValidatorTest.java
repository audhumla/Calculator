package com.ki.calculator.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.ki.calculator.domain.model.MathOperation.DIFFERENCE;
import static com.ki.calculator.domain.model.MathOperation.DIVISION;
import static com.ki.calculator.domain.model.MathOperation.MULTIPLICATION;
import static com.ki.calculator.domain.model.MathOperation.SUM;
import static org.assertj.core.api.Assertions.assertThat;

public class RightOperationValidatorTest {

    private final RightOperationValidator sut = new RightOperationValidator();

    @Nested
    class RightOperationIsCorrect {

        @ParameterizedTest
        @MethodSource("input")
        void shouldReturnTrueIfItsNotDivisionByZero(RightOperation rightOperation) {
            boolean actual = sut.test(rightOperation);

            assertThat(actual).isTrue();
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(SUM.with(5)),
                    Arguments.of(DIFFERENCE.with(0)),
                    Arguments.of(MULTIPLICATION.with(0)),
                    Arguments.of(DIVISION.with(1))
            );
        }
    }

    @Nested
    class RightOperationIsNotCorrect {

        @Test
        void shouldReturnFalseIfItsNotDivisionByZero() {
            boolean actual = sut.test(DIVISION.with(0));

            assertThat(actual).isFalse();
        }
    }
}