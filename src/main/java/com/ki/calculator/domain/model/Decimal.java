package com.ki.calculator.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;


public class Decimal {
    private static final int SCALE = 8;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final BigDecimal value;

    public static Decimal of(long value) {
        return new Decimal(
                BigDecimal.valueOf(value)
        );
    }

    public static Decimal of(double value) {
        return new Decimal(
                BigDecimal.valueOf(value)
        );
    }

    private Decimal(BigDecimal value) {
        this.value = value.setScale(SCALE, ROUNDING_MODE);
    }

    public boolean isPositive() { return ZERO.compareTo(value) < 0; }

    public boolean isZero() {
        return ZERO.compareTo(value) == 0;
    }

    public boolean isNegative() { return ZERO.compareTo(value) > 0; }

    public Decimal plus(Decimal other) {
        return new Decimal(value.add(other.value));
    }

    public Decimal minus(Decimal other) {
        return new Decimal(value.subtract(other.value));
    }

    public Decimal times(Decimal other) {
        return new Decimal(value.multiply(other.value));
    }

    public Decimal div(Decimal other) {
        if (other.isZero()) {
            throw new IllegalArgumentException("Cannot divide a number by zero");
        }
        return new Decimal(value.divide(other.value, ROUNDING_MODE));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Decimal that = (Decimal) other;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return new StringBuilder("Decimal{").append("value=").append(value).append('}').toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
