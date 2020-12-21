package com.ki.calculator.outbound;

import com.ki.calculator.domain.model.Decimal;
import com.ki.calculator.domain.model.Expression;
import com.ki.calculator.domain.model.MathOperation;
import com.ki.calculator.domain.model.RightOperation;

import java.util.ArrayList;
import java.util.List;

public class ExpressionMapper {
    private ExpressionMapper() { }

    private static final List<String> EXPECTED_WORDS = List.of("add", "multiply", "divide", "subtract", "apply");

    public static Expression convert(List<String> lines) {
        List<RightOperation> ops = new ArrayList<>(lines.size());
        Decimal leftOperand = null;
        for (String line : lines) {
            String[] splitString = parseLine(line);
            var operation = splitString[0];
            var number = splitString[1];
            if ("apply".equals(operation)) {
                leftOperand = toDecimal(number);
            } else {
                ops.add(new RightOperation(toOperation(operation), toDecimal(number)));
            }
        }

        if (leftOperand == null) {
            throw new IllegalArgumentException("apply not present in the file");
        }

        return new Expression(leftOperand).append(ops);
    }

    private static String[] parseLine(String line) {
        String[] lines = line.split(" ");
        if (lines.length < 2) {
            throw new IllegalArgumentException("line: " + line + " cannot be parsed since it does not contain two words");
        }
        if (!EXPECTED_WORDS.contains(lines[0])) {
            throw new IllegalArgumentException("line: " + line + "does not start with an expected word in the list: " + EXPECTED_WORDS);
        }
        if (!isNumeric(lines[1])) {
            throw new IllegalArgumentException("line: " + line + "does not provide a correct number as " + EXPECTED_WORDS);
        }
        return lines;
    }

    private static boolean isNumeric(String str) {
       return  str.chars().allMatch( Character::isDigit );
    }

    public static Decimal toDecimal(String numberAsString) {
        return Decimal.of(Long.parseLong(numberAsString));
    }

    public static MathOperation toOperation(String operationAsString) {
        MathOperation operation;
        switch (operationAsString) {
            case "add":
                operation = MathOperation.SUM;
                break;
            case "multiply":
                operation = MathOperation.MULTIPLICATION;
                break;
            case "divide":
                operation = MathOperation.DIVISION;
                break;
            case "subtract":
                operation = MathOperation.DIFFERENCE;
                break;
            default:
                throw new IllegalArgumentException("Cannot map input [" + operationAsString + "] to an operation");
        }
        return operation;
    }
}
