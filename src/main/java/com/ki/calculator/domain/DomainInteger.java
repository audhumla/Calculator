package com.ki.calculator.domain;

import java.math.BigInteger;
import java.util.Objects;

public class DomainInteger {
    private final BigInteger value;

    private DomainInteger(BigInteger value) {
        this.value = value;
    }

    public static DomainInteger of(long value) {
        return new DomainInteger(
                BigInteger.valueOf(value)
        );
    }

    public boolean isZero() {
        return BigInteger.ZERO.equals(value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        DomainInteger that = (DomainInteger) other;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
