package by.itacademy.sorter;


import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface SorterReading {

    /*Метод интерфейса SorterReading, который принимает в качестве аргумента Map processedTransport, и возвращает Map
    c ключом типа String и значением коллекцией List, типизируемой классом Transport*/
    Map<String, List<Transport>> readSorting(Map<String, List<Transport>> processedTransport) throws SorterReaderException;
}
