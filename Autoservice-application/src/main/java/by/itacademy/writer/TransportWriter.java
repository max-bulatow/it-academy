package by.itacademy.writer;

import by.itacademy.transport.Transport;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface TransportWriter {
    List<TransportDestination> write(final Map<String, List<Transport>> processedTransport, final Comparator<Transport> comparator) throws TransportWriterException;
}
