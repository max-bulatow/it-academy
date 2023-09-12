package by.itacademy.sorter;


import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface SorterReading {
    Map<String, List<Transport>> readSorting(final Map<String, List<Transport>> processedTransport) throws SorterReaderException;
}
