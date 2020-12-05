package com.ki.calculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DomainDecimal {

    private final BigDecimal value;

    private DomainDecimal(BigDecimal value) {
        this.value = value;
    }

    public static DomainDecimal of(long value) {
        return new DomainDecimal(
                BigDecimal.valueOf(value)
                        .setScale(8, RoundingMode.HALF_UP)
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        DomainDecimal that = (DomainDecimal) other;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
