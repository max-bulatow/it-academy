package by.itacademy.writer;

import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface TransportWriter {
    /*Метод интерфейса TransportWriter, который принимает в качестве аргумента Map processedTransport (обработанного и
    отсортированного транспорта), а возвращает коллекцию List типизируемую классом TransportDestination*/
    List<TransportDestination> write(final Map<String, List<Transport>> processedTransport) throws TransportWriterException;
}
