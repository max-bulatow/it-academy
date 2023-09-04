package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;
import java.util.function.Function;

public enum SortingDirection {
    FORWARD(Function.identity()),

    REVERSE(Comparator::reversed);

    private final Function<Comparator<Transport>, Comparator<Transport>> comparatorTransformer;

    SortingDirection(final Function<Comparator<Transport>, Comparator<Transport>> comparatorTransformer) {
        this.comparatorTransformer = comparatorTransformer;
    }

    public Comparator<Transport> transform(final Comparator<Transport> comparator) {
        return comparatorTransformer.apply(comparator);
    }
}
