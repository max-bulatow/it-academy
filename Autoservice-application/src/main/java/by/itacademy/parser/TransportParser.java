package by.itacademy.parser;

import by.itacademy.transport.Transport;

import java.util.List;

public interface TransportParser {
    /*Метод интерфейса TransportParser, который принимает в качестве аргумента String content, а возвращает
    коллекцию List, типизируемую классом Transport*/
    List<Transport> parse(String content) throws TransportParserException;

}
