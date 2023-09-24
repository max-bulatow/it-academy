package it.academy.core.sorter.impl;

import it.academy.core.sorter.SorterReaderException;
import it.academy.core.sorter.SortingDirection;
import it.academy.core.sorter.SortingReader;
import it.academy.core.sorter.SortingType;
import it.academy.core.transport.TransportContainer;

import java.util.HashMap;
import java.util.Map;

public class SorterReader implements SortingReader {
    @Override
    public TransportContainer readSorting(final String sortingContent, final TransportContainer transportContainer) throws SorterReaderException {
        final Map<SortingType, SortingDirection> sorterMap = new HashMap<>(SortingType.values().length);
        try {
            if (sortingContent != null) {
                final String[] parts = sortingContent.split(",\\s");
                for (String part : parts) {
                    final String[] sortingPair = part.split("-");


                    final SortingType sortingType = SortingType.valueOf(sortingPair[0].toUpperCase());
                    final SortingDirection sortingDirection = SortingDirection.valueOf(sortingPair[1].toUpperCase());
                    sorterMap.put(sortingType, sortingDirection);

                    if (sortingDirection.equals(SortingDirection.FORWARD)) {
                        transportContainer.getValidTransport().sort(sortingType.getComparator());
                    } else if (sortingDirection.equals(SortingDirection.REVERSE)) {
                        transportContainer.getValidTransport().sort(sortingType.getComparator().reversed());
                    }
                }
            }
        } catch (final IllegalArgumentException exception) {
            throw new SorterReaderException("Введена не корректная сортировка", exception);
        }
        return transportContainer;
    }
}
