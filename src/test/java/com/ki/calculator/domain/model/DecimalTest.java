package com.ki.calculator.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DecimalTest {

    @Nested
    class IsZeroTest {

        @Test
        void shouldReturnFalseIfNotZero() {
            Decimal input = Decimal.of(1);

            boolean actual = input.isZero();

            assertThat(actual).isFalse();
        }

        @Test
        void shouldReturnTrueIfZero() {
            Decimal input = Decimal.of(0);

            boolean actual = input.isZero();

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class IsPositiveTest {

        @Test
        void shouldReturnFalseIfNotZero() {
            Decimal input = Decimal.of(Double.MIN_VALUE);

            boolean actual = input.isPositive();

            assertThat(actual).isFalse();
        }

        @ParameterizedTest
        @CsvSource("1, 0")
        void shouldReturnTrueIfZero(int value) {
            Decimal input = Decimal.of(value);

            boolean actual = input.isPositive();

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class SumTest {

        @ParameterizedTest
        @MethodSource("input")
        void shouldComputeSum(Decimal leftOperand, Decimal rightOperand, Decimal result) {
            assertThat(leftOperand.plus(rightOperand)).isEqualTo(result);
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(Decimal.of(5.3), Decimal.of(5.03), Decimal.of(10.33)),
                    Arguments.of(Decimal.of(-1.1), Decimal.of(1.15), Decimal.of(0.05))
            );
        }

        @Test
        void sumShouldHandleOverflow() {
            Decimal actual = Decimal.of(Long.MAX_VALUE).plus(Decimal.of(1));
            assertThat(actual.isPositive()).isTrue();
        }
    }

    @Nested
    class DifferenceTest {
        @ParameterizedTest
        @MethodSource("input")
        void shouldComputeDifference(Decimal left, Decimal right, Decimal result) {
            assertThat(left.minus(right)).isEqualTo(result);
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(Decimal.of(5), Decimal.of(5), Decimal.of(0)),
                    Arguments.of(Decimal.of(Long.MAX_VALUE), Decimal.of(Long.MAX_VALUE), Decimal.of(0)),
                    Arguments.of(Decimal.of(Long.MAX_VALUE).plus(Decimal.of(1)), Decimal.of(Long.MAX_VALUE), Decimal.of(1))
            );
        }
    }

    @Nested
    class DivisionTest {

        @ParameterizedTest
        @MethodSource("input")
        void shouldComputeDivision(Decimal left, Decimal right, Decimal result) {
            assertThat(left.div(right)).isEqualTo(result);
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(Decimal.of(5), Decimal.of(5), Decimal.of(1)),
                    Arguments.of(Decimal.of(Long.MAX_VALUE), Decimal.of(Long.MAX_VALUE), Decimal.of(1)),
                    Arguments.of(Decimal.of(1000), Decimal.of(10), Decimal.of(100))
            );
        }

        @Test
        void divisionByZeroThrowsException() {
            Decimal aNumber = Decimal.of(1);
            Decimal zero = Decimal.of(0);
            assertThatThrownBy(
                    () -> aNumber.div(zero)
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Cannot divide a number by zero");
        }
    }

    @Nested
    class MultiplicationTest {
        @ParameterizedTest
        @MethodSource("input")
        void shouldComputeMultiplication(Decimal left, Decimal right, Decimal result) {
            assertThat(left.times(right)).isEqualTo(result);
        }

        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(Decimal.of(5), Decimal.of(5), Decimal.of(25)),
                    Arguments.of(Decimal.of(Long.MIN_VALUE), Decimal.of(0), Decimal.of(0)),
                    Arguments.of(Decimal.of(-1), Decimal.of(-1), Decimal.of(1))
            );
        }
    }
}