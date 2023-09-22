package it.academy.core.sorter;

import it.academy.core.transport.TransportContainer;


public interface SortingReader {
    TransportContainer readSorting(String step, TransportContainer transportContainer) throws SorterReaderException;
}
