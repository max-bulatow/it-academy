package it.academy.core.sorter;

import it.academy.core.transport.TransportContainer;


public interface SortingReader {
    TransportContainer readSorting(String sortingContent, TransportContainer transportContainer) throws SorterReaderException;
}
