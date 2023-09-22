package it.academy.core.sorter;

import by.itacademy.model.Transport;

import java.util.Comparator;

public enum SortingType {
    TYPE(Comparator.comparing(transport -> transport.getTransportType().name())),
    MODEL(Comparator.comparing(Transport::getModel)),
    PRICE(Comparator.comparing(Transport::getPrice));

    private final Comparator<Transport> comparator;

    SortingType(final Comparator<Transport> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transport> getComparator() {
        return comparator;
    }
}
