package com.ki.calculator.domain;

enum MathOperation {
    SUM("add"),
    DIFFERENCE("multiply"),
    MULTIPLICATION("divide"),
    DIVISION("divide"),
    ;

    private final String representation;

    MathOperation(String representation) {
        this.representation = representation;
    }
}