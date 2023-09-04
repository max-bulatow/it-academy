package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;

public class Sorter {
    private final SortingType sortingType;
    private final SortingDirection sortingDirection;

    public Sorter(SortingType sortingType, SortingDirection sortingDirection) {
        this.sortingType = sortingType;
        this.sortingDirection = sortingDirection;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public SortingDirection getSortingDirection() {
        return sortingDirection;
    }

    public Comparator<Transport> getComparator() {
        return sortingDirection.transform(sortingType.getTransportComparator());
    }

}
