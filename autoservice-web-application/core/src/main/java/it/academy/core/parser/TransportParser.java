package it.academy.core.parser;

import by.itacademy.model.Transport;

import java.util.List;

public interface TransportParser {
    List<Transport> parse(String content) throws TransportParserException;
}
