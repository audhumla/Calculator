package com.ki.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ki.calculator.domain.MathOperation.DIFFERENCE;
import static com.ki.calculator.domain.MathOperation.DIVISION;
import static com.ki.calculator.domain.MathOperation.SUM;
import static com.ki.calculator.domain.Rule.isDivisionByZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

public class LineValidatorTest {

    @Nested
    @TestInstance(PER_CLASS)
    class LineIsCorrect {
        @SuppressWarnings("unused")
        Stream<Arguments> input() {
            return Stream.of(
                    Arguments.of(new Line(SUM, 5)),
                    Arguments.of(new Line(DIFFERENCE, 0)),
                    Arguments.of(new Line(DIVISION, 1))
            );
        }

        @ParameterizedTest
        @MethodSource("input")
        void shouldReturnTrue(Line line) {
            assertThat(isDivisionByZero.test(line)).isFalse();
        }
    }
    @Nested
    static class LineIsNotCorrect {


    }

}