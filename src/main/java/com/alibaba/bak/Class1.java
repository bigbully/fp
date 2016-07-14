package com.alibaba.bak;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

import static java.lang.Math.abs;

/**
 * User: bigbully
 * Date: 16/7/14
 * Time: 下午10:49
 */
public class Class1 {

    String formatAbs(int x) {
        String msg = "The absolute value of %d is %d.";
        return String.format(msg, x, abs(x));
    }

    String formatFactorial(int n) {
        String msg = "The factorial of %d is %d.";
        return String.format(msg, n, factorial(n));
    }


    String formatAbs_(int x) {
        return formatResult("Abs", x, (Integer i)-> abs(i));
    }

    String formatFactorial_(int n) {
        return formatResult("Factorial", n, (Integer i) -> factorial(i));
    }

    String formatResult(String name, int n, Function<Integer, Integer> f) {
        String msg = "The %s of %d is %d.";
        return String.format(msg, name, n, f.apply(n));
    }

    <A, B> String formatResult(String name, A n, Function<A, B> f) {
        String msg = "The %s of %s is %s.";
        return String.format(msg, name, n, f.apply(n));
    }

    String double2String(double d) {
        return String.valueOf(d);
    }

    int factorial(int n) {

        IntBinaryOperator go = new IntBinaryOperator() {
            @Override
            public int applyAsInt(int n, int acc) {
                if (n <= 0) return acc;
                else return applyAsInt(n - 1, n * acc);
            }
        };

        return go.applyAsInt(n, 1);
    }

    <A, B, C> Function<B, C> partail1(A a, BiFunction<A, B, C> f) {
        return b -> f.apply(a, b);
    }

    <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> f) {
        return a -> b -> f.apply(a, b);
    }

    <A, B, C> BiFunction<A, B, C> uncurry(Function<A, Function<B, C>> f) {
        return (a, b) -> f.apply(a).apply(b);
    }

    <A, B, C> Function<A, C> compose(Function<B, C> f, Function<A, B> g) {
        return a -> f.apply(g.apply(a));
    }


}
