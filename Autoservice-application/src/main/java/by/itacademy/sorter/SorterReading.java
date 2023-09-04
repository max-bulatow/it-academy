package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;

public interface SorterReading {
    Comparator<Transport> readSorting() throws SorterReaderException;
}
