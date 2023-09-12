package by.itacademy.reader;

import by.itacademy.transport.Transport;

import java.util.List;

public interface TransportReader {

    /*Метод интерфейса TransportReader, который возвращает колллекцию List, типизируемую классом Transport*/
    List<Transport> read() throws TransportReaderException;
}
