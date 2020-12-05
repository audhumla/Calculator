package com.ki.calculator.domain;

import java.util.function.Predicate;

class Rule {
        static Predicate<Line> isNumberZero = line -> line.getNumber().isZero();
        static Predicate<Line> isDivision = line -> line.getOperation() == MathOperation.DIVISION;
        static Predicate<Line> isDivisionByZero = line -> isDivision.test(line) && isNumberZero.test(line);
}