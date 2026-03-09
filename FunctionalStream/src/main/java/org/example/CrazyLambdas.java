package org.example;
import java.util.Random;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class CrazyLambdas {

    public static Supplier<String> helloSupplier() {
        return () -> "Hello";
    }

    public static Predicate<String> isEmptyPredicate() {
        return s -> s.isEmpty();
    }

    public static BiFunction<String, Integer, String> stringMultiplier() {
        return (s, n) -> s.repeat(n);
    }

    public static Function<BigDecimal, String> toDollarStringFunction() {
        return bd -> "$" + bd.toString();
    }

    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return s -> s.length() >= min && s.length() < max;
    }

    public static IntSupplier randomIntSupplier() {
        Random random = new Random();
        return () -> random.nextInt();
    }

    public static IntUnaryOperator boundedRandomIntSupplier() {
        Random random = new Random();
        return bound -> random.nextInt(bound);
    }

    public static IntUnaryOperator intSquareOperation() {
        return x -> x * x;
    }

    public static LongBinaryOperator longSumOperation() {
        return (a, b) -> a + b;
    }

    public static ToIntFunction<String> stringToIntConverter() {
        return s -> Integer.parseInt(s);
    }

    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return () -> x -> n * x;
    }

    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return f -> s -> f.apply(s.trim());
    }

    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        return () -> {
            Thread thread = new Thread(runnable);
            thread.start();
            return thread;
        };
    }

    public static Consumer<Runnable> newThreadRunnableConsumer() {
        return runnable -> new Thread(runnable).start();
    }

    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
        return runnable -> () -> {
            Thread thread = new Thread(runnable);
            thread.start();
            return thread;
        };
    }

    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        return (function, predicate) -> x ->
                predicate.test(x) ? function.applyAsInt(x) : x;
    }

    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        return (map, name) -> map.getOrDefault(name, IntUnaryOperator.identity());
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> mapper) {
        return (o1, o2) -> mapper.apply(o1).compareTo(mapper.apply(o2));
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(
            Comparator<? super T> comparator, Function<? super T, ? extends U> mapper) {
        return (o1, o2) -> {
            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
            return mapper.apply(o1).compareTo(mapper.apply(o2));
        };
    }

    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        return () -> () -> () -> "WELL DONE!";
    }
}
