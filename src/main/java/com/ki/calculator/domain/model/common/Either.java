package com.ki.calculator.domain.model.common;

import java.util.function.Function;

public class Either<L, R> {
    private L left = null;
    private R right = null;

    private Either(L l,R r) {
        left = l;
        right = r;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left,null);
    }

    public L left() {
        return left;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public R right() {
        return right;
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public <V> V fold(final Function<? super L, V> ifLeft, final Function<? super R, V> ifRight) {
        if (left != null)
            return ifLeft.apply(left);
        else if (right != null)
            return ifRight.apply(right);
        else
            throw new IllegalStateException("Cannot apply fold to Either when left and right are both null");
    }
}
