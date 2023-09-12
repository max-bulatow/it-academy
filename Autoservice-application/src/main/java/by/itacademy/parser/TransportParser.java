package by.itacademy.parser;

import by.itacademy.transport.Transport;

import java.util.List;

public interface TransportParser {
    /*метод интерфейса TransportParser, который принимает в качестве аргументов String content, а возвращает
    коллекцию List, типизируемая классом Transport*/
    List<Transport> parse(String content) throws TransportParserException;

}
